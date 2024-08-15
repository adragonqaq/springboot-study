package com.lzl.rabbitmq.controller;

import com.lzl.rabbitmq.producter.Producter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaofu
 * @Description:
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private Producter producter;

    @GetMapping(value = "/send")
    @ResponseBody
    public void send() {
        producter.send();
    }

    @GetMapping(value = "/send2")
    @ResponseBody
    public void send2() {
        producter.send2();
    }

    @GetMapping(value = "/send3")
    @ResponseBody
    public void send3() {
        producter.send3();
    }

    @GetMapping(value = "/send4")
    @ResponseBody
    public void send4() {
        producter.send4();
    }

    @GetMapping(value = "/send5")
    @ResponseBody
    public void send5() {
        producter.send5();
    }
}
