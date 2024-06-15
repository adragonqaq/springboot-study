package com.lzl.okHttp;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
public class OkHttpPostTest {


    /**
     *
     * 请求头：
     * 同样的，也可能同时存在多个名称一样的请求头，调用header(name, value)方法就可以设置请求头的name和value，
     * 调用该方法会确保整个请求头中不会存在多个名称一样的name。如果想添加多个name相同的请求头，应该调用addHeader(name, value)方法，这样可以添加重复name的请求头，其value可以不同
     *
     * Request request = new Request.Builder()
     *         .url("https://api.github.com/repos/square/okhttp/issues")
     *         .header("User-Agent", "OkHttp Headers.java")
     *         .addHeader("Accept", "application/json; q=0.5")
     *         .addHeader("Accept", "application/vnd.github.v3+json")
     *         .build();
     *
     *
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //1、创建一个 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        //2、创建Request对象
        User user = User.builder().id(2L).name("houchen").build();
        String str = JSONObject.toJSONString(user);
        Request request = new Request.Builder()
                .url("http://localhost:8082/addUser")
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str))
                .build();
        //通过Call 来执行同步或异步请求，调用execute方法同步执行，调用enqueue方法异步执行
        Response response = client.newCall(request).execute();
        //通过Response对象的isSuccessful()方法可以判断请求是否成功。
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        /*String s = new String(response.body().bytes());
        System.out.println(s);*/
        log.info(response.body().string());
    }
}
