package com.lzl.httpclient;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
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



    public static void test(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet = new HttpGet("http://central.maven.org/maven2/"); // 创建httpget实例
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(10000) // 设置连接超时时间10秒钟
                .setSocketTimeout(20000) // 设置读取超时时间10秒钟
                .build();
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        CloseableHttpResponse response = httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity = response.getEntity(); // 获取返回实体
        System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
    }


    //http中的长连接和短连接你真的了解吗？
    //https://blog.csdn.net/weixin_45701550/article/details/136766671

    // 短连接
    public static void test2(String[] args) throws Exception {
        HttpClient client = HttpClients.createDefault();
        for (int i = 0; i < 10; i++) {
            InputStream in = null;
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8086/req");
            Header[] headerArrays = new Header[]{new BasicHeader("Connection", "close")};
            httpGet.setHeaders(headerArrays);
            try {
                HttpResponse response = client.execute(httpGet);
                System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
                in = response.getEntity().getContent();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
            }
        }

    }



    // 长连接
    public static void test3(String[] args) throws Exception {
        HttpClient client = HttpClients.createDefault();
        for (int i = 0; i < 10; i++) {
            InputStream in = null;
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8086/req");
            Header[] headerArrays = new Header[]{new BasicHeader("Connection", "Keep-Alive")};
            httpGet.setHeaders(headerArrays);
            try {
                HttpResponse response = client.execute(httpGet);
                System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
                in = response.getEntity().getContent();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
            }
        }

    }




}

