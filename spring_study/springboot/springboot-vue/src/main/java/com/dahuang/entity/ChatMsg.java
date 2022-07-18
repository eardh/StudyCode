package com.dahuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsg {
    private String send_username;
    private String receive_username;
    private String sendtext;
    private Date sendtime;
    private String msgtype;
}
