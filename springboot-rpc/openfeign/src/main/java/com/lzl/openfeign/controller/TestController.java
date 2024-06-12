package com.lzl.openfeign.controller;


import com.lzl.openfeign.api.TestApi;
import com.lzl.openfeign.bean.User;
import feign.Feign;
import feign.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

@RestController
@RequestMapping("feign")
public class TestController {


    @GetMapping("/test")
    public String test() throws Exception {

        TestApi client = Feign.builder()
                //设置连接和读超时间都是5s  如果不设置  默认的就是连接超时10s，读超时60s 源码有配置
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
                //设置连接和读超时间都是5s  这里可以去掉，为了体现具体走的哪个超市配置，故保留
                .options(new Request.Options(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, true))
                .target(TestApi.class, "http://localhost:8088");

        User user = client.queryUser2(123,new Request.Options(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, true));

        return user.getName();
    }







}
