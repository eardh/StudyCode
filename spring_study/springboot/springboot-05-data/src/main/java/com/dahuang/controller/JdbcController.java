package com.dahuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @GetMapping("/userlist")
//    public List<Map<String,Object>> userList(){
//        String sql = "select * from user";
//        List<Map<String,Object>> list_map = jdbcTemplate.queryForList(sql);
//        return list_map;
//    }
//
//    @GetMapping("/adduser")
//    public String addUser(){
//        String sql = "insert into user(id,name,pwd) values(10,'小李','123456')";
//        jdbcTemplate.update(sql);
//        return "add-ok";
//    }
//
//    @GetMapping("/updateuser/{id}")
//    public String updateUser(@PathVariable("id") int id){
//        String sql = "update user set name=?,pwd=? where id="+id;
//        Object[] objects = new Object[2];
//        objects[0] = "小离";
//        objects[1] = "32423432";
//        jdbcTemplate.update(sql,objects);
//        return "update-ok";
//    }
//
//    @GetMapping("/deleteuser/{id}")
//    public String deleteUser(@PathVariable("id") int id){
//        String sql = "delete from user where id=?";
//        jdbcTemplate.update(sql,id);
//        return "delete-ok";
//    }
}
