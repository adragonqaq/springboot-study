package com.lzl.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


// 开启hytrix
@EnableHystrix
// 开启断路器Dashboard监控仪表盘
// 访问入口：http://localhost:启动端口/hystrix，然后再地址栏上输入:http://localhost:启动端口/actuator/hystrix.stream
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}