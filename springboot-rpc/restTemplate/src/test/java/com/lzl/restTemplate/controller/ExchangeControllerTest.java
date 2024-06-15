package com.lzl.restTemplate.controller;

import com.lzl.restTemplate.Bean.ResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;



@SpringBootTest
class ExchangeControllerTest {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 文件上传，post请求
     */
    @Test
    public void upload(){
        //需要上传的文件
        String filePath = "/Users/panzhi/Desktop/Jietu20220205-194655.jpg";

        //请求地址
        String url = "http://localhost:8080/upload";

        // 请求头设置,multipart/form-data格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //提交参数设置
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("uploadFile", new FileSystemResource(new File(filePath)));
        //服务端如果接受额外参数，可以传递
        param.add("userName", "张三");

        // 组装请求体
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(param, headers);

        //发起请求
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }





    /**
     * 小文件下载
     *
     *
     * 这种下载方法实际上是将下载文件一次性加载到客户端本地内存，然后从内存将文件写入磁盘。
     * 这种方式对于小文件的下载还比较适合，如果文件比较大或者文件下载并发量比较大，容易造成内存的大量占用，从而降低应用的运行效率
     *
     *
     * @throws IOException
     */
    @Test
    public void downloadFile() throws IOException {
        String userName = "张三";
        String fileName = "c98b677c-0948-46ef-84d2-3742a2b821b0.jpg";
        //请求地址
        String url = "http://localhost:8080/downloadFile/{1}/{2}";

        //发起请求,直接返回对象（restful风格）
        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class, userName,fileName);
        System.out.println("文件下载请求结果状态码：" + rsp.getStatusCode());

        // 将下载下来的文件内容保存到本地
        String targetPath = "/Users/panzhi/Desktop/"  + fileName;
        Files.write(Paths.get(targetPath), Objects.requireNonNull(rsp.getBody(), "未获取到下载文件"));
    }



    /**
     * 大文件下载
     *
     * 这种下载方式的区别在于：
     * 设置了请求头APPLICATION_OCTET_STREAM，表示以流的形式进行数据加载
     * RequestCallback结合File.copy保证了接收到一部分文件内容，就向磁盘写入一部分内容。而不是全部加载到内存，最后再写入磁盘文件。
     * 在下载大文件时，例如excel、pdf、zip等等文件，特别管用，
     *
     *
     * @throws IOException
     */
    @Test
    public void downloadBigFile() throws IOException {
        String userName = "张三";
        String fileName = "c98b677c-0948-46ef-84d2-3742a2b821b0.jpg";
        //请求地址
        String url = "http://localhost:8080/downloadFile/{1}/{2}";

        //定义请求头的接收类型
        RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        //对响应进行流式处理而不是将其全部加载到内存中
        String targetPath = "/Users/panzhi/Desktop/"  + fileName;
        restTemplate.execute(url, HttpMethod.GET, requestCallback, clientHttpResponse -> {
            Files.copy(clientHttpResponse.getBody(), Paths.get(targetPath));
            return null;
        }, userName, fileName);
    }

}