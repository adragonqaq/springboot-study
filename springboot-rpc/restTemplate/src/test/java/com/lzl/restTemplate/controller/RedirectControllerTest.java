package com.lzl.restTemplate.controller;

import com.lzl.restTemplate.Bean.RequestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
class RedirectControllerTest {


    @Autowired
    private RestTemplate restTemplate;
    /**
     * 重定向，post请求
     */
    @Test
    public void testPostByLocation(){
        //请求地址
        String url = "http://localhost:8080/testPostByLocation";
        //入参
        RequestBean request = new RequestBean();
        request.setUserName("唐三藏");
        request.setUserPwd("123456789");

        //用于提交完成数据之后的页面跳转，返回跳转url
        URI uri = restTemplate.postForLocation(url, request);
        System.out.println(uri.toString());
    }

//    输出结果如下：
//    http://localhost:8080/index.html

}