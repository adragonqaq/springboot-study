package com.lzl.hystrix.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("hystrix")
public class TestController {


    @Resource
    public RestTemplate restTemplate;


    /**
     *
     * 必须要启动 @EnableHystrix  要不然不生效
     *
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "getFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String test() throws InterruptedException {
        // 模拟超时
        Thread.sleep(3000);
        return "hahahhah";
    }




    @GetMapping("/test2")
    @HystrixCommand(fallbackMethod = "getFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String test2() throws InterruptedException {
        // 模拟超时
        Thread.sleep(3000);
        return restTemplate.getForObject("www.baidu.com",String.class);
    }


    public String getFallback(){
        return "我降级了";
    }

}
