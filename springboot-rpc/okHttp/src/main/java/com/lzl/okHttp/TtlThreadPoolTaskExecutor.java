package com.lzl.okHttp;


import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 增强Spring的ThreadPoolTaskExecutor
 *
 * @author eren.liao
 * @date 2022/6/28 17:41
 */
@Slf4j
public class TtlThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {


    @Override
    public void execute(Runnable command) {
        Runnable ttlRunnable = TtlRunnable.get(command);
        super.execute(ttlRunnable);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Callable ttCallable = TtlCallable.get(task);
        return super.submit(ttCallable);
    }


    @Override
    public Future<?> submit(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        return super.submit(ttlRunnable);
    }


    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        return super.submitListenable(ttlRunnable);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        Callable ttlCallable = TtlCallable.get(task);
        return super.submitListenable(ttlCallable);
    }

}