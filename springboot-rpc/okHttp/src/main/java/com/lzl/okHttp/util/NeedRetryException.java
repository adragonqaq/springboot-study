package com.lzl.okHttp.util;

public class NeedRetryException extends Exception{

    public NeedRetryException(String message) {
        super("NeedRetryException can retry."+message);
    }
}
