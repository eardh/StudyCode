package com.dahuang.service;

import com.dahuang.mapper.UserMapper;
import com.dahuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
