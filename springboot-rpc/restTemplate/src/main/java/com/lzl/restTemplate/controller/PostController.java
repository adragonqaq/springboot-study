package com.lzl.restTemplate.controller;


import com.alibaba.fastjson2.JSON;
import com.lzl.restTemplate.Bean.RequestBean;
import com.lzl.restTemplate.Bean.ResponseBean;
import org.springframework.web.bind.annotation.*;


/**
 *
 *POST请求方法和GET请求方法上大同小异，RestTemplate的POST请求也包含两个主要方法：
 *
 * postForObject():返回body对象
 * postForEntity():返回全部的信息
 *
 *
 *
 */
@RestController
public class PostController {


    /**
     * 模拟表单请求，post方法测试
     * @return
     */
    @RequestMapping(value = "testPostByForm", method = RequestMethod.POST)
    public ResponseBean testPostByForm(@RequestParam("userName") String userName,
                                       @RequestParam("userPwd") String userPwd){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testPostByForm，请求参数userName：" + userName + ",userPwd:" + userPwd);
        return result;
    }


    /**
     * 模拟表单请求，post方法测试
     * @param request
     * @return
     */
    @RequestMapping(value = "testPostByFormAndObj", method = RequestMethod.POST)
    public ResponseBean testPostByForm(RequestBean request){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testPostByFormAndObj，请求参数：" + JSON.toJSONString(request));
        return result;
    }

    /**
     * 模拟JSON请求，post方法测试
     * @param request
     * @return
     */
    @RequestMapping(value = "testPostByJson", method = RequestMethod.POST)
    public ResponseBean testPostByJson(@RequestBody RequestBean request){
        ResponseBean result = new ResponseBean();
        result.setCode("200");
        result.setMsg("请求成功，方法：testPostByJson，请求参数：" + JSON.toJSONString(request));
        return result;
    }







}
