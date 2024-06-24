package com.lzl.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * 不要被 spring ioc扫到 要不然全局生效
 */
public class ClientApiConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Logger feignLogger() {
        return new FeignLogger();
    }
}
