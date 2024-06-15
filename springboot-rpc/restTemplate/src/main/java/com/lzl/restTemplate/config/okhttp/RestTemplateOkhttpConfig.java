package com.lzl.restTemplate.config.okhttp;


import com.alibaba.fastjson2.JSON;
import com.lzl.restTemplate.Bean.RestLog;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *
 * https://www.cnblogs.com/hellosyl/p/18054333
 *
 */
@Configuration
public class RestTemplateOkhttpConfig {


    @Value("${okhttp.connect-timeout}")
    private Integer connectTimeout;

    @Value("${okhttp.read-timeout}")
    private Integer readTimeout;

    @Value("${okhttp.write-timeout}")
    private Integer writeTimeout;

    @Value("${okhttp.max-idle-connections}")
    private Integer maxIdleConnections;

    @Value("${okhttp.keep-alive-duration}")
    private Integer keepAliveDuration;


    @Bean("httpRestTemplate")
    public RestTemplate httpRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        // 设置消息转换，解决中文乱码问题
        for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
            // 原有的String是ISO-8859-1编码 ，设置为UTF-8
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                break;
            }
        }
        // 增加日志拦截器
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpRequestLogInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean("httpsRestTemplate")
    public RestTemplate httpsRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpsRequestFactory());
        // 设置消息转换，解决中文乱码问题
        for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
            // 原有的String是ISO-8859-1编码 ，设置为UTF-8
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                break;
            }
        }
        // 增加日志拦截器
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpRequestLogInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * HTTP request factory
     */
    private ClientHttpRequestFactory httpRequestFactory() {
        // 创建连接池
        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
        // 创建okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectionPool(connectionPool)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, sslSession) -> true)
                .retryOnConnectionFailure(true)
                // 设置代理
//              .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
//                .addInterceptor()
                .build();
        // 返回ClientHttpRequestFactory
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    /**
     * HTTPS request factory
     */
    private ClientHttpRequestFactory httpsRequestFactory() {
        // 创建连接池
        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
        // 信任管理
        X509TrustManager trustManager = null;
        SSLContext sslContext = null;
        try {
            trustManager = new X509TrustManager() {
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            // 信任任何链接
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectionPool(connectionPool)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, sslSession) -> true)
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                .retryOnConnectionFailure(true)
                // 设置代理
//              .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
//                .addInterceptor()
                .build();
        // 返回ClientHttpRequestFactory
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }


    @Slf4j
    static class HttpRequestLogInterceptor implements ClientHttpRequestInterceptor {
        /*
        也可以使用okhttp预置的日志拦截器，放在OkHttpClient的拦截器集合里
        <!--添加日志拦截器-->
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>logging-interceptor</artifactId>
            <version>3.14.9</version>
        </dependency>
        // 日志拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        */

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            requestLog(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            responseLog(response);
            return response;
        }

        private void requestLog(HttpRequest request, byte[] body) {
            log.info("==================== request start ====================");
            log.info("Uri         :{}", request.getURI());
            log.info("Method      :{}", request.getMethod());
            log.info("Headers     :{}", request.getHeaders());
            log.info("Request Body:{}", new String(body, StandardCharsets.UTF_8));
            log.info("==================== request end ====================");
        }

        private void responseLog(ClientHttpResponse response) throws IOException {
            log.info("==================== response start ====================");
            log.info("Status Code  :{}", response.getStatusCode());
            log.info("Status Text  :{}", response.getStatusText());
            log.info("Headers      :{}", response.getHeaders());
            log.info("Response Body:{}", IOUtils.toString(response.getBody(), StandardCharsets.UTF_8));
            log.info("==================== response end ====================");
        }
    }





    @Slf4j
    static class MyRequestInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            //记录请求开始时间
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //执行请求
            ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
            //执行完毕后，这里要创建备份，要不然会报io提前关闭的异常错误
            ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
            //记录请求结束时间
            stopWatch.stop();
            //获取响应内容
            StringBuilder resBody = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseCopy.getBody(),
                    Charset.forName("UTF-8")))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    resBody.append(line);
                    line = bufferedReader.readLine();
                }
            }

            //获取请求内容
            String reqBody = "";
            //这里可以自定义想打印什么方法或者请求的内容，如下只打印post请求的日志，因为这时候才会带上body
            if (httpRequest.getHeaders().getContentType() != null && "POST".equals(httpRequest.getMethod().name())) {
                String requestBody = new String(body, Charset.forName("UTF-8"));
//                String contentType = httpRequest.getHeaders().getContentType().toString();
                //这里可以对contentType做一些判断，针对其格式化请求体的内容，如"application/x-www-form-urlencoded"格式会附带一些boundary(分割线)的内容
                reqBody = requestBody;

            }

            //打印日志的格式可以自定义
            log.info(JSON.toJSONString(RestLog.builder().costTime(stopWatch.getLastTaskTimeMillis()).headers(httpRequest.getHeaders()).method(httpRequest.getMethodValue())
                    .reqUrl(httpRequest.getURI().toString()).reqBody(reqBody).resBody(resBody.toString()).resStatus(responseCopy.getRawStatusCode()).build()));

            return responseCopy;
        }
    }


    /**
     * 响应内容备份
     */
    final static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private byte[] body;


        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }


        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }
    }




}