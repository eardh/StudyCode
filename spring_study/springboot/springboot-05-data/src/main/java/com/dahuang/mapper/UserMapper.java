package com.dahuang.mapper;

import com.dahuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个 mybatis 的 mapper 类；
@Repository
@Mapper
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(@Param("id") int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("id") int id);
}
