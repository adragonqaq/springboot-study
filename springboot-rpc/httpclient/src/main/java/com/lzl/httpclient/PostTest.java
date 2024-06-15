package com.lzl.httpclient;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class PostTest {
    public static void main(String... args) throws IOException {
        // 1. 创建HttpClient实例
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 2. 创建HttpPost实例
        HttpPost httpPost = new HttpPost("http://localhost:8082/addUser");
        User user = User.builder().id(2L).name("houchen").build();
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setEntity(new StringEntity(JSONObject.toJSONString(user), "UTF-8"));
        // 3. 调用HttpClient实例来执行HttpPost实例
        CloseableHttpResponse response = httpclient.execute(httpPost);
        // 4. 读 response
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            System.out.println("res-->" + res);
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
        // 5. 释放连接
        response.close();
        httpclient.close();
    }
}
