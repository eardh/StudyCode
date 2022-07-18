package com.dahuang.interfaces;

import com.dahuang.beans.MethodInfo;
import com.dahuang.beans.ServerInfo;

/**
 *  rest请求调用handler
 */
public interface RestHandler {

    /**
     *  初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     *  调用rest请求，返回接口
     * @param serverInfo
     * @param methodInfo
     * @return
     */
    Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo);

}
