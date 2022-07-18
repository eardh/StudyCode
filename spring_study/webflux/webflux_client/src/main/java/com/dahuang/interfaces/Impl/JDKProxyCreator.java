package com.dahuang.interfaces.Impl;

import com.dahuang.annotation.ApiServer;
import com.dahuang.beans.MethodInfo;
import com.dahuang.beans.ServerInfo;
import com.dahuang.interfaces.ProxyCreator;
import com.dahuang.interfaces.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  使用jdk动态代理实现代理类
 */
@Slf4j
public class JDKProxyCreator implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {

        log.info("createProxy:"+type);

        // 根据接口得到API服务器信息
        ServerInfo serverInfo = extractServerInfo(type);

        log.info("serverInfo:"+serverInfo);

        // 给每一个代理类一个实现
        RestHandler handler = new WebClientRestHandler();

        // 初始化服务器信息(初始化webclient)
        handler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        // 根据方法和参数得到调用信息
                        MethodInfo methodInfo = extractMethodInfo(method,args);

                        log.info("methodInfo:"+methodInfo);

                        // 调用rest
                        return handler.invokeRest(serverInfo,methodInfo);

                    }

                    /**
                     *  根据方法定义和调用参数得到调用的相关信息
                     * @param method
                     * @param args
                     * @return
                     */
                    private MethodInfo extractMethodInfo(Method method, Object[] args) {

                        MethodInfo methodInfo = new MethodInfo();

                        // 得到请求URL和请求方法
                        Annotation[] annotations = method.getAnnotations();

                        for ( Annotation annotation:annotations ) {

                            // GET
                            if(annotation instanceof GetMapping) {
                                GetMapping mapping = (GetMapping) annotation;
                                methodInfo.setUrl(mapping.value()[0]);
                                methodInfo.setMethod(HttpMethod.GET);
                            }
                            // POST
                            else if(annotation instanceof PostMapping) {
                                PostMapping mapping = (PostMapping) annotation;
                                methodInfo.setUrl(mapping.value()[0]);
                                methodInfo.setMethod(HttpMethod.POST);

                            }
                            // DELETE
                            else if(annotation instanceof DeleteMapping) {
                                DeleteMapping mapping = (DeleteMapping) annotation;
                                methodInfo.setUrl(mapping.value()[0]);
                                methodInfo.setMethod(HttpMethod.DELETE);

                            }
                            // PUT
                            else if(annotation instanceof PutMapping) {
                                PutMapping mapping = (PutMapping) annotation;
                                methodInfo.setUrl(mapping.value()[0]);
                                methodInfo.setMethod(HttpMethod.PUT);
                            }
                        }

                        // 得到调用的参数和body
                        Parameter[] parameters = method.getParameters();

                        // 参数和对应的map
                        Map<String,Object> params = new LinkedHashMap<>();
                        methodInfo.setParams(params);

                        for ( int i = 0; i < parameters.length;i++) {
                            // 是否带 @PathVariable
                            PathVariable anPath = parameters[i].getAnnotation(PathVariable.class);
                            if( anPath != null) {
                                params.put(anPath.value(),args[i]);
                            }

                            // 是否带 @RequestBody
                            RequestBody anBody = parameters[i].getAnnotation(RequestBody.class);
                            if( anBody != null) {
                                methodInfo.setBody((Mono<?>) args[i]);
                                // 请求对象的实际类型
                                methodInfo.setReturnElementType(extractElementType(parameters[i].getParameterizedType()));
                            }
                        }

                        // 提取返回对象信息
                        extractReturnInfo(method,methodInfo);
                        return methodInfo;
                    }

                    private void extractReturnInfo(Method method, MethodInfo methodInfo) {

                        // 返回flux还是mono
                        // isAssignableFrom判断类型是否为某类的子类
                        // instanceof 判断实例是否为某个的子类
                        boolean ifFlux = method.getReturnType().isAssignableFrom(Flux.class);
                        methodInfo.setReturnFlux(ifFlux);

                        // 得到返回对象的实际类型

                        Class<?> elementType = extractElementType(method.getGenericReturnType());

                        methodInfo.setReturnElementType(elementType);

                    }

                    private Class<?> extractElementType(Type genericReturnType) {
                        Type[] types = ((ParameterizedType) genericReturnType).getActualTypeArguments();

                        return (Class<?>) types[0];
                    }
                });
    }


    /**
     *  提取服务器注解信息
     * @param type
     * @return
     */
    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();

        ApiServer apiServer = type.getAnnotation(ApiServer.class);

        serverInfo.setUrl(apiServer.value());

        return serverInfo;
    }
}
