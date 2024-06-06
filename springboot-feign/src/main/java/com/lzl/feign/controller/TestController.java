package com.lzl.feign.controller;


import com.lzl.feign.api.TestApi;
import com.lzl.feign.bean.User;
import feign.Feign;
import feign.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("feign")
public class TestController {


    @GetMapping("/test")
    public String test() throws Exception {

        TestApi client = Feign.builder()
                //设置连接和读超时间都是5s
                .options(new Request.Options(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, true))
                .target(TestApi.class, "http://localhost:8088");

        User user = client.queryUser(123);

        return user.getName();
    }


    /**
     *
     * 这两种设置超时时间的主要区别就是
     * 方法参数设置超时时间的 优先级  高于Feign.Builder设置的超时时间
     *
     *
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/test2")
    public String test2() throws Exception {

        TestApi client = Feign.builder()
                //设置连接和读超时间都是5s
                .options(new Request.Options(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, true))
                .target(TestApi.class, "http://localhost:8088");

        User user = client.queryUser2(123,new Request.Options(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, true));

        return user.getName();
    }







}
