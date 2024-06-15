package com.lzl.restTemplate.controller;


import com.lzl.restTemplate.Bean.ResponseBean;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 *
 * 通过RestTemplate发送HTTP GET协议请求，经常使用到的方法有两个：
 *
 * getForObject():返回值是HTTP协议的响应体
 * getForEntity():返回的是ResponseEntity，ResponseEntity是对HTTP响应的封装，除了包含响应体，还包含HTTP状态码、contentType、contentLength、Header等信息
 * 在Spring Boot环境下写一个单元测试用例，首先创建一个Api接口，然后编写单元测试进行服务测试。
 *
 */
@RestController
public class GetController {


    /**
     * 不带参的get请求
     * @return
     */
    @GetMapping(value = "testGet")
    public ResponseBean testGet(){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testGet");
        return result;
    }


    /**
     * 带参的get请求(restful风格)
     * @return
     */
    @RequestMapping(value = "testGetByRestFul/{id}/{name}", method = RequestMethod.GET)
    public ResponseBean testGetByRestFul(@PathVariable(value = "id") String id, @PathVariable(value = "name") String name){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testGetByRestFul，请求参数id：" +  id + "请求参数name：" + name);
        return result;
    }



    /**
     * 带参的get请求(使用占位符号传参)
     * @return
     */
    @RequestMapping(value = "testGetByParam", method = RequestMethod.GET)
    public ResponseBean testGetByParam(@RequestParam("userName") String userName,
                                       @RequestParam("userPwd") String userPwd){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testGetByParam，请求参数userName：" +  userName + ",userPwd：" + userPwd);
        return result;
    }





}
