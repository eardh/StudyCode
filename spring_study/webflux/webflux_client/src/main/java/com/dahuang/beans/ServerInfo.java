package com.dahuang.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务信息类
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    // 服务器url
    private String url;
}
