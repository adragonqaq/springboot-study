package com.lzl.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormSubmitTest {
    public static void main(String[] args) throws IOException {
        // 1. 创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 设置表单参数
        List<NameValuePair> kv =  new ArrayList<>();
        kv.add(new BasicNameValuePair("id", "1"));
        kv.add(new BasicNameValuePair("name", "zhangsan"));
        // 3. 创建HttpPost实例
        HttpPost httpPost = new HttpPost("http://localhost:8082/addUser");
        // 4. 让Post携带表单参数
        httpPost.setEntity(new UrlEncodedFormEntity(kv, Consts.UTF_8));
        // 5. 获取HttpResponse响应
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 6. 读response
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            System.out.println("res-->" + res);
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
        // 7. 释放资源
        response.close();
        httpClient.close();
    }
}

