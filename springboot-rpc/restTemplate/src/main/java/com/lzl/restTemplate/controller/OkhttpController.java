package com.lzl.restTemplate.controller;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OkhttpController {

    @Resource
    private RestTemplate httpRestTemplate;


    @GetMapping("/okhttp")
    public void okhttp() {

        String requestUrl = "https://api.uomg.com/api/icp";
        //构建json请求参数
        JSONObject params = new JSONObject();
        params.put("domain", "www.baidu.com");
        //请求头声明请求格式为json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //创建请求实体
        HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
        //执行post请求，并响应返回字符串
        String resText = httpRestTemplate.postForObject(requestUrl, entity, String.class);
        System.out.println(resText);
    }


}
