package com.lzl.springai.mcp.openfeign;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/29 16:43
 **/
@SpringBootApplication
@EnableFeignClients({"com.lzl.springai.mcp.openfeign.feign"})
public class McpFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpFeignApplication.class, args);
    }
}
    
    
    