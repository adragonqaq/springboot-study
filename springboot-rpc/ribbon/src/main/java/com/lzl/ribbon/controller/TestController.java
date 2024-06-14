package com.lzl.ribbon.controller;


import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import com.netflix.niws.client.http.RestClient;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("ribbon")
public class TestController {

    @GetMapping("/test")
    public String test() throws Exception {


        // 设置user服务的两个服务实例地址
// 设置Ribbon客户端的服务列表，这里假设服务名为"your-service-name"
        ConfigurationManager.getConfigInstance().setProperty(
                "your-service-name.ribbon.listOfServers", "localhost:8080");

        // 获取user服务对应的RestClient，这RestClient就可以用来发送http请求
        RestClient client =(RestClient) ClientFactory.getNamedClient("user");

        // 构建一个http请求
        HttpRequest httpRequest = HttpRequest.newBuilder().uri("/user/123").build();

        // 发送http请求，以负载均衡的方式
        HttpResponse httpResponse = client.executeWithLoadBalancer(httpRequest);


        return "success";
    }



}
