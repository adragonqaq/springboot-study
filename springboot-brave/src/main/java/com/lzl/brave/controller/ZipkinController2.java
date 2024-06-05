package com.lzl.brave.controller;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("zipkin")
public class ZipkinController2 {
    public static final String url = "http://localhost:8083/zipkin/service3";
    public static final String url2 = "http://localhost:8084/zipkin/service4";

    @Autowired
    OkHttpClient client;

    @GetMapping("/service2")
    public String service() throws Exception {
        System.out.println("loading-----");
        Request request1 = new Request.Builder().url(url).build();
        Request request2 = new Request.Builder().url(url2).build();

        Response response1 = client.newCall(request1).execute();
        Response response2 = client.newCall(request2).execute();
        return "con2 + "+ response1.body().string() + "-" + response2.body().string();
    }
}