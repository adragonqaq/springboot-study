package com.lzl.okHttp.util;


@FunctionalInterface
public interface RetryerConsumer {

    Boolean apply();
}
