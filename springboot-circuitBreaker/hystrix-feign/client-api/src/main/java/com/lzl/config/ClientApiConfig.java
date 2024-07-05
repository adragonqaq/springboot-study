package com.lzl.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

import static java.util.concurrent.TimeUnit.SECONDS;


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


    @Bean
    public Retryer retryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),2);
    }

}
