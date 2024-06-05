package com.lzl.brave.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zipkin")
public class ZipkinController5 {

    @GetMapping("/service5")
    public String service() throws Exception {

        return "service5 -----";
    }
}