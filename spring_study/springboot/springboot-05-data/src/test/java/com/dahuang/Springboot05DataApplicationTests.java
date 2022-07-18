package com.dahuang;

import com.dahuang.mapper.UserMapper;
import com.dahuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class Springboot05DataApplicationTests {


    @Autowired
    DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {

        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
