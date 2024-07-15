package com.lzl.okHttp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class PrivilegesCardThreadPoolConfig {



    @Bean
    public ThreadPoolTaskExecutor privilegesThreadPoolExecutor() {

        TtlThreadPoolTaskExecutor executor = new TtlThreadPoolTaskExecutor();
        //设置线程名称
        executor.setThreadNamePrefix("privileges-pool-");
        //设置最大线程数
        executor.setMaxPoolSize(10);
        //设置核心线程数
        executor.setCorePoolSize(4);
        //设置线程空闲时间，默认60
        executor.setKeepAliveSeconds(60);
        //设置队列容量
        executor.setQueueCapacity(200);
        // 线程池打满后主线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
