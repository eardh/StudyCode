package com.dahuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String nickname;
    private String password;
    private String usign;
    private String uimg;
    private String perms;
}
