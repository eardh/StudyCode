package com.dahuang.config.handler;

import com.dahuang.entity.ChatMsg;
import com.dahuang.entity.ChatMsg1;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.omg.CORBA.portable.InputStream;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * WebSocket处理器
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    // 保存所有用户 session
    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();


    /**
     *  socket建立连接事件
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String username = (String) session.getAttributes().get("username");
        SESSION_POOL.put(username,session);
        System.out.println(username);

        if (session.getId() != null) {
            // 用户连接成功，放入在线用户池
            SESSION_POOL.put(username,session);
        } else {
            throw new RuntimeException("用户登录已经失效！");
        }
    }


    /**
     *  接收消息事件
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 获取客户端传来的消息
        if (message instanceof TextMessage) {
            ChatMsg chatMsg = new Gson().fromJson(message.getPayload().toString(), ChatMsg.class);
            System.out.println(chatMsg.getSend_username());
            WebSocketSession ws = SESSION_POOL.get(chatMsg.getReceive_username());
            String msg = chatMsg.getSendtext();
            System.out.println(msg);
            ws.sendMessage(new TextMessage(msg));
        } else if(message instanceof BinaryMessage) {
            ChatMsg1 chatMsg1 = new Gson().fromJson((JsonReader) message.getPayload(), ChatMsg1.class);
            System.out.println(chatMsg1.getSend_username());
            WebSocketSession ws = SESSION_POOL.get(chatMsg1.getReceive_username());
            InputStream msg = (InputStream) chatMsg1.getSendtext();
            int len = 0;
            byte[] buff= new byte[1024];
            while((len=msg.read(buff))!=-1){
                ws.sendMessage(new BinaryMessage(buff));
            }

        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    /**
     *  关闭连接事件
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = (String) session.getAttributes().get("username");
        System.out.println("关闭");
        System.out.println(session.getId());
        if(username != null){
            SESSION_POOL.remove(username);
            System.out.println(username);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
