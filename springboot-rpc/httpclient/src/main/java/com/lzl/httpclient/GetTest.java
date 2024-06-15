package com.lzl.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetTest {
    public static void main(String[] args) throws IOException {
        // 1. 创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2. 创建GET请求方法实例
        HttpGet httpGet = new HttpGet("http://localhost:8082/getName");
        // 3. 调用HttpClient实例来执行GET请求方法，得到response
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 4. 读response，判断是否访问成功 2xx表示 成功。
        int status = response.getStatusLine().getStatusCode();
        if (status == 200 ) {
            // 对得到后的实例可以进行处理，例如读取回复体，读取html
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            System.out.println("远程调用--->" + res);
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }

        // 6. 释放连接
        response.close();
        httpClient.close();
    }













}

