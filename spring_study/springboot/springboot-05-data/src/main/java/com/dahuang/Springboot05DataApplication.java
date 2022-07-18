package com.dahuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.dahuang.mapper") //扫描包配置
public class Springboot05DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05DataApplication.class, args);
    }

}
