package com.dahuang.interfaces;

import org.springframework.stereotype.Component;

/**
 *  创建代理类接口
 */
@Component
public interface ProxyCreator {

    Object createProxy(Class<?> type);
}
