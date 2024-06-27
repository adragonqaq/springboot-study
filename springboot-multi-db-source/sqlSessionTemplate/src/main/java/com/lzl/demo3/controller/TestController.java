package com.lzl.demo3.controller;



import com.lzl.demo3.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    @Autowired
    @Qualifier("db1SqlSessionTemplate")
    private SqlSessionTemplate bank1Template;

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSessionTemplate bank2Template;

    @GetMapping("test")
    public void test() {
        List<User> list = bank1Template.selectList("com.lzl.demo3.mapper.UserMapper.selectAll");
        System.out.printf("个数：%d%n", list.size());
        bank2Template.insert("com.lzl.demo3.mapper.UserMapper.batchInsert",list);
        System.out.println("批量插入成功");
    }

}