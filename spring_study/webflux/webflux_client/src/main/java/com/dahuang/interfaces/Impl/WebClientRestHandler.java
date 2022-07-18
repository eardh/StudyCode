package com.dahuang.interfaces.Impl;

import com.dahuang.beans.MethodInfo;
import com.dahuang.beans.ServerInfo;
import com.dahuang.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientRestHandler implements RestHandler {

    private WebClient webClient;

    /**
     *  初始化webclient
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    /**
     *  处理rest请求
     * @param serverInfo
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo) {

        Object result = null;

        WebClient.RequestBodySpec request = this.webClient
                // 请求的方法
                .method(methodInfo.getMethod())
                // 请求url
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                // 接受类型
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec retrieve = null;

        // 判断是否存在body
        if ( methodInfo.getBody() != null ) {
             // 发出请求
             retrieve = request.body(methodInfo.getBody(), methodInfo.getReturnElementType())
                    .retrieve();
        } else {
            retrieve = request.retrieve();
        }

        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,response -> Mono.just(new RuntimeException("Not Found")));

        // 处理body
        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
