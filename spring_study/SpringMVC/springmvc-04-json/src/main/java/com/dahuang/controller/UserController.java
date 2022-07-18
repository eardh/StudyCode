package com.dahuang.controller;

import com.alibaba.fastjson.JSON;
import com.dahuang.pojo.User;
import com.dahuang.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller //跳转页面
@RestController //返回字符串
public class UserController {

    //加了 @ResponseBody 不会走视图解析器，会直接返回一个字符串
    //@ResponseBody
    @RequestMapping(value = "/j1") //produces = "application/json;charset=utf-8" 可以解决乱码问题
    public String json1() throws JsonProcessingException {

        //jaskson , ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //创建一个对象
        User user = new User("哒哒",4,"男");

        String string = objectMapper.writeValueAsString(user);

        return string;
    }

    @RequestMapping(value = "/j2")
    public String json2() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = new ArrayList<>();
        User user1 = new User("1哒哒",4,"男");
        User user2 = new User("2哒哒",4,"男");
        User user3 = new User("3哒哒",4,"男");
        User user4 = new User("4哒哒",4,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JsonUtils.getJson(userList);
    }

    @RequestMapping(value = "/j3")
    public String json3() throws JsonProcessingException {

        Date date = new Date();
        return JsonUtils.getJson(date);
    }

    @RequestMapping(value = "/j4")
    public String json4() throws JsonProcessingException {

        List<User> userList = new ArrayList<>();
        User user1 = new User("1哒哒",4,"男");
        User user2 = new User("2哒哒",4,"男");
        User user3 = new User("3哒哒",4,"男");
        User user4 = new User("4哒哒",4,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String string = JSON.toJSONString(userList);
        return string;
    }
}
