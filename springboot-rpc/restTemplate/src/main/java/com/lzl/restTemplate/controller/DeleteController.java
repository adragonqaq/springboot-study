package com.lzl.restTemplate.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 * delete方法协议，表示删除一个已经存在的资源，该方法会向URL代表的资源发送一个HTTP DELETE方法请求
 *
 */
@RestController
public class DeleteController {


    /**
     * 模拟JSON请求，delete方法测试
     * @return
     */
    @RequestMapping(value = "testDeleteByJson", method = RequestMethod.DELETE)
    public void testDeleteByJson(){
        System.out.println("请求成功，方法：testDeleteByJson");
    }

}
