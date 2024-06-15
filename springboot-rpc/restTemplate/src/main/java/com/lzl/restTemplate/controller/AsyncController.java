package com.lzl.restTemplate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@RestController
public class AsyncController {



    @Resource
    private RestTemplate restTemplate;


    @Autowired
    @Qualifier("threadPoolExecutor")
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     *
     * AsyncRestTemplate是在Spring4.0中对RestTemplate进行扩展产生的新类，
     * 通过返回ListenableFuture对象生成回调机制，以达到异步非阻塞发送http请求
     *
     * 缺点：
     *
     * AsyncRestTemplate已经过期
     * AsyncRestTemplate调用服务时，被调用服务不可达的时候，会抛出异常
     * AsyncRestTemplate不能携带traceId

     * @return
     */
    @GetMapping("/sync/hello")
    public String syncHello() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        String url = "http://localhost:8083/sync/hello";//休眠5秒的服务
        //调用完后立即返回（没有阻塞）
        ListenableFuture<ResponseEntity<String>> forEntity = template.getForEntity(url, String.class);
        //异步调用后的回调函数
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            //调用失败
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("远程调用faliure");
            }
            //调用成功
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                System.out.println("res---->"+result.getBody());
            }
        });
        System.out.println("调用结束");
        return "ok";
    }



    /**
     * @Description: 通过CompletableFuture异步发起请求
     * @author houChen
     * @date 2022/9/24 20:18
     */
    @GetMapping("/sync/hello1")
    public String syncHello1() {
        CompletableFuture.supplyAsync(() -> {
                    String url = "http://localhost:8083/sync/hello";//休眠5秒的服务
                    return restTemplate.getForEntity(url, String.class);
                }, threadPoolExecutor)
                .thenAccept(res -> {
                    System.out.println("res: " + res.getBody());
                }).exceptionally(e -> {
                    System.out.println("err:"+ e);
                    return null;
                });
        return "ok";
    }



    @GetMapping("/sync/hello2")
    // 指定使用自己定义的线程池中的线程就行调用
    @Async("threadPoolExecutor")
    public String syncHello2() {

        String url = "http://localhost:8083/sync/hello";//休眠5秒的服务
        String res = restTemplate.getForEntity(url, String.class).getBody();
        System.out.println("res :"+ res);
        return "ok";
    }


}
