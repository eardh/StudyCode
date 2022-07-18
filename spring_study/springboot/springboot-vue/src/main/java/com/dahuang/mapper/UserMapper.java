package com.dahuang.mapper;

import com.dahuang.entity.ChatFriend;
import com.dahuang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    User queryUserById(@Param("username") String username);

    List<ChatFriend> queryFriendById(@Param("username") String username);
}
