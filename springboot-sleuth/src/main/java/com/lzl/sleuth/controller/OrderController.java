package com.lzl.sleuth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
public class OrderController {
    @GetMapping("/create")
    public String creatOrder() {
        System.out.println("create order");
        return "create_order";
    }
}