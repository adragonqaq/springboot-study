package com.lzl.okHttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Test3 {

    @Autowired
    @Qualifier("privilegesThreadPoolExecutor")
    private ThreadPoolTaskExecutor privilegesThreadPoolExecutor;





}
