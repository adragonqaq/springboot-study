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
public class ZipkinController3 {
    public static final String url = "http://localhost:8084/zipkin/service4";

    @Autowired
    OkHttpClient client;

    @GetMapping("/service3")
    public String service() throws Exception {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return "con3 + "+ response.body().string();
    }
}