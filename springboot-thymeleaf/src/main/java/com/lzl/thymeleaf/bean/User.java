package com.lzl.thymeleaf.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private  int age;
    private  String detail;

}
