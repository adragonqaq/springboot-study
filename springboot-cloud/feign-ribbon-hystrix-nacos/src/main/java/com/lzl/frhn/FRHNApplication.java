package com.lzl.frhn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class FRHNApplication {

    public static void main(String[] args) {
        SpringApplication.run(FRHNApplication.class, args);
    }

}