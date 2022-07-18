package com.dahuang.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document("user") // 表示user表
@Data
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10,max = 100)
    private int age;

}
