package com.lzl.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("client")
public class TestController {

    @GetMapping("/sayHello")
    public String sayHello() throws InterruptedException {

        System.out.println("有请求进来了");
        // 模拟超时
        Thread.sleep(1000);

        return "hello";
    }

}