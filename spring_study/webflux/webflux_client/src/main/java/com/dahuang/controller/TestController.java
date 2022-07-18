package com.dahuang.controller;

import com.dahuang.api.IUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test1() {
        // 测试信息提取
        // 不订阅，不会实际发出请求，但会进去代理类
//        userApi.getAllUser();
//        userApi.getUserById("123434");
//        userApi.deleteUserById("123");
//        userApi.createUser(Mono.just(User.builder().name("test").age(22).build()));
//        userApi.updateUser("123123",Mono.just(User.builder().name("test2").age(22).build()));

        // 直接调用，实现rest接口效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::println);

        String id = "60a107d37d0a3a5a908125e5";
        userApi.getUserById(id).subscribe( user -> System.out.println(user),e -> System.err.println("找不到用户:"+e.getMessage()));

        userApi.getUserById("60a0eb4cbf865e2e29495181").subscribe( user -> System.out.println(user),e -> System.err.println("找不到用户:"+e.getMessage()));

//        userApi.deleteUserById(id).subscribe(System.out::println);

//        userApi.createUser(Mono.just(User.builder().name("大狗").age(23).build()))
//                .subscribe(System.out::println);
//        userApi.updateUser("60a0eb4cbf865e2e29495181",Mono.just(User.builder().name("hh").age(44).build()))
//                .subscribe(System.out::println);
    }
}
