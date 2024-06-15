package com.lzl.restTemplate.controller;

import com.lzl.restTemplate.Bean.RequestBean;
import com.lzl.restTemplate.Bean.ResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
class PostControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 模拟表单提交，post请求
     */
    @Test
    public void testPostByForm(){
        //请求地址
        String url = "http://localhost:8080/testPostByForm";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userName", "唐三藏");
        map.add("userPwd", "123456");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        //发起请求
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }




    /**
     * 模拟表单提交，post请求
     */
    @Test
    public void testPostByForm2(){
        //请求地址
        String url = "http://localhost:8080/testPostByFormAndObj";
        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userName", "唐三藏");
        map.add("userPwd", "123456");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        //发起请求
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }





    /**
     * 模拟JSON提交，post请求
     */
    @Test
    public void testPostByJson(){
        //请求地址
        String url = "http://localhost:8080/testPostByJson";

        //入参
        RequestBean request = new RequestBean();
        request.setUserName("唐三藏");
        request.setUserPwd("123456789");

        //发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }













}