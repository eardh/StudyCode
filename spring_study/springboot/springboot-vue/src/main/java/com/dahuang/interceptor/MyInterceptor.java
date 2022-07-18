package com.dahuang.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class MyInterceptor implements HandshakeInterceptor {

    /**
     *  握手前
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("握手前");

        /**
         * websocket系统启动连接程序，启动的时候就会把他的session值传过来，放入到websocketsession（websocket的一个内置服务器）里面
         */

        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        String username = servletRequest.getServletRequest().getParameter("username");
        if (username == "" || username == null) {
            return false;
        }
        attributes.put("username", username);
        return true;
    }

    /**
     *  握手后
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("握手后");
        //super.afterHandshake(request, response, wsHandler, exception);
    }
}
