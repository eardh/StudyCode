package com.dahuang.controller;

import com.dahuang.mapper.UserMapper;
import com.dahuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/adduser")
    public String addUser() {
        userMapper.addUser(new User(10, "hh", "3432432"));
        return "add-ok";
    }

    @GetMapping("/updateuser")
    public String updateUser() {
        userMapper.updateUser(new User(10, "yy", "3432432"));
        return "update-ok";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userMapper.deleteUser(id);
        return "delete-ok";
    }
}
