package com.dahuang.service;


import com.dahuang.entity.ChatFriend;
import com.dahuang.entity.ChatMsg;
import com.dahuang.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User queryUserById(String username);

    List<ChatFriend> queryFriendById(String username);

}
