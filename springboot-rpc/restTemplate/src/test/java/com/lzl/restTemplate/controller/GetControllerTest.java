package com.lzl.restTemplate.controller;

import com.lzl.restTemplate.Bean.ResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;



@SpringBootTest
class GetControllerTest {

    @Autowired
    private RestTemplate restTemplate;



    /**
     * 单元测试（不带参的get请求）
     */
    @Test
    public void testGet(){
        //请求地址
        String url = "http://localhost:8080/testGet";

        //发起请求,直接返回对象
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class);
        System.out.println(responseBean.toString());
    }


    /**
     * 单元测试（带参的get请求）
     */
    @Test
    public void testGetByRestFul(){
        //请求地址
        String url = "http://localhost:8080/testGetByRestFul/{1}/{2}";

        //发起请求,直接返回对象（restful风格）
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class, "001", "张三");
        System.out.println(responseBean.toString());
    }


    /**
     * 单元测试（带参的get请求）
     */
    @Test
    public void testGetByParam(){
        //请求地址
        String url = "http://localhost:8080/testGetByParam?userName={userName}&userPwd={userPwd}";

        //请求参数
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("userName", "唐三藏");
        uriVariables.put("userPwd", "123456");

        //发起请求,直接返回对象（带参数请求）
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class, uriVariables);
        System.out.println(responseBean.toString());
    }



    /**
     * 单元测试
     *
     * 上面的所有的getForObject请求传参方法，getForEntity都可以使用，使用方法上也几乎是一致的，只是在返回结果接收的时候略有差别。
     *
     * 使用ResponseEntity<T> responseEntity来接收响应结果。用responseEntity.getBody()获取响应体。
     *
     */
    @Test
    public void testAllGet(){
        //请求地址
        String url = "http://localhost:8080/testGet";

        //发起请求，返回全部信息
        ResponseEntity<ResponseBean> response = restTemplate.getForEntity(url, ResponseBean.class);

        // 获取响应体
        System.out.println("HTTP 响应body：" + response.getBody().toString());

        // 以下是getForEntity比getForObject多出来的内容
        HttpStatus statusCode = response.getStatusCode();
        int statusCodeValue = response.getStatusCodeValue();
        HttpHeaders headers = response.getHeaders();

        System.out.println("HTTP 响应状态：" + statusCode);
        System.out.println("HTTP 响应状态码：" + statusCodeValue);
        System.out.println("HTTP Headers信息：" + headers);
    }





}