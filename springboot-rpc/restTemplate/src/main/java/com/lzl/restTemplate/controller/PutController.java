package com.lzl.restTemplate.controller;

import com.alibaba.fastjson2.JSON;
import com.lzl.restTemplate.Bean.RequestBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * put请求方法，可能很多人都没用过，它指的是修改一个已经存在的资源或者插入资源，该方法会向URL代表的资源发送一个HTTP PUT方法请求，示例如下
 */
@RestController
public class PutController {

    /**
     * 模拟JSON请求，put方法测试
     * @param request
     * @return
     */
    @RequestMapping(value = "testPutByJson", method = RequestMethod.PUT)
    public void testPutByJson(@RequestBody RequestBean request){
        System.out.println("请求成功，方法：testPutByJson，请求参数：" + JSON.toJSONString(request));
    }
}
