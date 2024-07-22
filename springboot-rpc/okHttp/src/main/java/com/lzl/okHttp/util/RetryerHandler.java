package com.lzl.okHttp.util;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;


@Slf4j
@Component
public class RetryerHandler {


    private Throwable throwable;

    public void retry(Retryer retryer, RetryerConsumer consumer){
        try {
            retryer.call(()-> {
                return consumer.apply();
                });
        }catch (RetryException ex){
            // 超过重试次数
            log.error("超过重试次数");
        }catch (Throwable t){
            // 其他异常
            this.throwable = t;
        }
    }

//    public <U> RetryerHandler<U> exceptionally(Function<Throwable, U> handler) {
//        if (this.throwable != null) {
//
//            return new RetryerHandler<>(() -> handler.apply(this.throwable));
//        }
//        // 如果没有异常，就返回当前对象的副本
//        return (RetryerHandler<U>) this;
//    }

}
