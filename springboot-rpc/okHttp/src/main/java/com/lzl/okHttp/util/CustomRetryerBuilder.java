package com.lzl.okHttp.util;


import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CustomRetryerBuilder {

    public Retryer createOrderRetryBuild() {

        //定义重试机制
        return RetryerBuilder.<Boolean>newBuilder()

                //retryIf 重试条件
                //.retryIfException()
                //.retryIfRuntimeException()
                //.retryIfExceptionOfType(Exception.class)
                //.retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))
                .retryIfExceptionOfType(NeedRetryException.class)

                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))

                //停止策略 : 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))

                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                // 版本有冲突，建议不使用
                // .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

                //默认的阻塞策略：线程睡眠
                //.withBlockStrategy(BlockStrategies.threadSleepStrategy())
                //自定义阻塞策略：自旋锁
                .withBlockStrategy(new SpinBlockStrategy())

                //自定义重试监听器
                .withRetryListener(new RetryLogListener())

                .build();

    }


    public Retryer testRetryBuild() {

        //定义重试机制
        return RetryerBuilder.<Boolean>newBuilder()

                .retryIfResult(Predicates.equalTo(false))
                .retryIfExceptionOfType(NeedRetryException.class)
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                //默认的阻塞策略：线程睡眠
                //.withBlockStrategy(BlockStrategies.threadSleepStrategy())
                .withBlockStrategy(new SpinBlockStrategy())
                .withRetryListener(new RetryLogListener())
                .build();
    }
}