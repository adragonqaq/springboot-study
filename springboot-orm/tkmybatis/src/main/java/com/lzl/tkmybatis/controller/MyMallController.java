package com.lzl.tkmybatis.controller;

import com.lzl.tkmybatis.service.MymallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMallController {
    @Autowired
    private MymallService mymallService;

    @GetMapping("mall")
    public String getRoles(){
        return mymallService.getRoles().toString();
    }
}