package com.dahuang;

import com.dahuang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot07ShiroApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("小明"));
    }

}
