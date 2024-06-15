package com.lzl.okHttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OkhttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkhttpApplication.class, args);
    }


    /**
     *
     *
     *
     * 可以通过OkHttpClient.Builder的connectTimeout()方法设置客户端和服务器建立连接的超时时间，
     * 通过writeTimeout()方法设置客户端上传数据到服务器的超时时间，
     * 通过readTimeout()方法设置客户端从服务器下载响应数据的超时时间。
     *
     * 在2.5.0版本之前，Okhttp默认不设置任何的超时时间，从2.5.0版本开始，Okhttp将连接超时、写入超时（上传数据）、读取超时（下载数据）的超时时间都默认设置为10秒。
     * 如果HTTP请求需要更长时间，那么需要我们手动设置超时时间。
     *
     * client = new OkHttpClient.Builder()
     *         .connectTimeout(10, TimeUnit.SECONDS)
     *         .writeTimeout(10, TimeUnit.SECONDS)
     *         .readTimeout(30, TimeUnit.SECONDS)
     *         .build();
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */

}