package com.dahuang.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 *  方法调用类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {

    // 请求url
    private String url;

    // 请求方法
    private HttpMethod method;

    // 请求参数
    private Map<String,Object> params;

    // 请求body
    private Mono<?> body;

    // 返回flux还是mono
    private boolean returnFlux;

    // 返回对象的类型
    private Class<?> returnElementType;

}
