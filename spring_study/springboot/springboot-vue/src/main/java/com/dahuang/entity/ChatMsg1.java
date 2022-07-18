package com.dahuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsg1 {

    private String send_username;
    private String receive_username;
    private InputStream sendtext;
    private Date sendtime;
    private String msgtype;
}
