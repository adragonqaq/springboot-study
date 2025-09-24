package com.lzl.geohash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 骑手实时派单系统启动类
 */
@SpringBootApplication
public class RiderDispatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiderDispatchApplication.class, args);
        System.out.println("骑手实时就近派单系统启动成功！");
    }
}

    
    
    