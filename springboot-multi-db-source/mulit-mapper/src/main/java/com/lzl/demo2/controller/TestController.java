package com.lzl.demo2.controller;


import com.lzl.demo2.entity.User;
import com.lzl.demo2.mapper.db1.User1Mapper;
import com.lzl.demo2.mapper.db2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @GetMapping("test")
    public void test() {
        List<User> list = user1Mapper.selectAll();
        System.out.printf("个数：%d%n", list.size());
        user2Mapper.batchInsert(list);
        System.out.println("批量插入成功");
    }

}