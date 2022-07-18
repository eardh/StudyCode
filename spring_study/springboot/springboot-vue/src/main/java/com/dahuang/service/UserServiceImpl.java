package com.dahuang.service;

import com.dahuang.entity.ChatFriend;
import com.dahuang.entity.User;
import com.dahuang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserById(String username) {
        return userMapper.queryUserById(username);
    }

    @Override
    public List<ChatFriend> queryFriendById(String username) {
        return userMapper.queryFriendById(username);
    }
}
