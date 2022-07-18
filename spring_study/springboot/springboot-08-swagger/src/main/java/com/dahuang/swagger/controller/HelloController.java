package com.dahuang.swagger.controller;

import com.dahuang.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("hello控制类")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //只要我们的接口中，返回值中存在实体类，他就会扫描到swagger中
    @PostMapping ("/user")
    public User user(){
        return new User();
    }

    // Operation接口，不是放在类上的,是方法
    @ApiOperation("hello控制类")
    @GetMapping("/hello1")
    public String hello1(@ApiParam("用户名") String username){
        return "hello1"+username;
    }

    //只要我们的接口中，返回值中存在实体类，他就会扫描到swagger中
    @PostMapping ("/user1")
    public User user1(@ApiParam("用户") User user){
        return user;
    }
}
