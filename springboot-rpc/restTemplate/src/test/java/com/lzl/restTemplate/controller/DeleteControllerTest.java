package com.lzl.restTemplate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
class DeleteControllerTest {



    @Autowired
    private RestTemplate restTemplate;
    /**
     * 模拟JSON提交，delete请求
     */
    @Test
    public void testDeleteByJson(){
        //请求地址
        String url = "http://localhost:8080/testDeleteByJson";

        //模拟JSON提交，delete请求
        restTemplate.delete(url);
    }

}