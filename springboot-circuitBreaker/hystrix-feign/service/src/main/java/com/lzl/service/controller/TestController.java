package com.lzl.service.controller;


import com.lzl.client.api.ClientApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    public ClientApi clientApi;

    @GetMapping("/sayHello")
    public String sayHello(){
        return clientApi.sayHello();
    }

}
