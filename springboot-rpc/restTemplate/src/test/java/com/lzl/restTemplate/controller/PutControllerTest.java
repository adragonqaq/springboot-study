package com.lzl.restTemplate.controller;

import com.lzl.restTemplate.Bean.RequestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class PutControllerTest {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 模拟JSON提交，put请求
     */
    @Test
    public void testPutByJson(){
        //请求地址
        String url = "http://localhost:8080/testPutByJson";
        //入参
        RequestBean request = new RequestBean();
        request.setUserName("唐三藏");
        request.setUserPwd("123456789");

        //模拟JSON提交，put请求
        restTemplate.put(url, request);
    }

}