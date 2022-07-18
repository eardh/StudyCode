package com.dahuang.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Phone {

    @Id
    private String ID;
    private String name;
    private String type;
}
