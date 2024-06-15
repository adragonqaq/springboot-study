package com.lzl.okHttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class OkHttpAsyncGetTest {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8082/getName")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String s = new String(response.body().bytes());
                log.info("res--->:{}", s);
                log.info("异步请求执行完毕！！！");
            }
        });
        log.info("主线程执行完毕！！！");
    }
}

