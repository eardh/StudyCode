package com.dahuang.config;

import com.dahuang.config.handler.MyWebSocketHandler;
import com.dahuang.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(myWebSocketHandler, "ws")
                .addInterceptors(myInterceptor)
                .setAllowedOrigins("*");
    }
}
