package com.dahuang.handlers;

import com.dahuang.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;


@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        // 设置响应头400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        // 设置返回类型
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);

        // 异常信息
        String errorMsg = toStr(throwable);

        DataBuffer wrap = response.bufferFactory().wrap(errorMsg.getBytes());

        return response.writeWith(Mono.just(wrap));
    }

    private String toStr(Throwable throwable) {
        // 已知异常
        if (throwable instanceof CheckException) {
            CheckException ex = (CheckException) throwable;
            return ex.getFieldName() + ":invalid value-"+ ex.getFieldValue();
        } else {
            // 未知异常，需要打印堆栈，方便定位
             throwable.printStackTrace();
             return throwable.toString();
        }
    }
}
