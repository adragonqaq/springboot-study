package com.lzl.okHttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpGetTest {


    /**
     *
     * 操作步骤：
     *
     * 创建OkHttpClient对象
     * 创建Request对象
     * 将Request 对象封装为Call
     * 通过Call 来执行同步或异步请求，调用execute方法同步执行，调用enqueue方法异步执行
     *
     * client执行newCall方法会得到一个Call对象，表示一个新的网络请求
     * Call对象的execute方法是同步方法，会阻塞当前线程，其返回Response对象
     * 通过Response对象的isSuccessful()方法可以判断请求是否成功
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //1、创建一个 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        //2、创建Request对象
        Request request = new Request.Builder()
                .url("http://localhost:8082/getName")
                .build();
        //通过Call 来执行同步或异步请求，调用execute方法同步执行，调用enqueue方法异步执行
        Response response = client.newCall(request).execute();
        //通过Response对象的isSuccessful()方法可以判断请求是否成功。
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        String s = new String(response.body().bytes());
        System.out.println(s);
    }
}
