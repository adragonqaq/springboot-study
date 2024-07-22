package com.lzl.restTemplate.config;

import okhttp3.OkHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {


    /**
     * 没有实例化RestTemplate时，初始化RestTemplate
     *
     * 这种用的是 使用了JDK自带的HttpURLConnection作为底层HTTP客户端实现
     * @return
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate1(){
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(restTemplateInterceptor()));
        return restTemplate;
    }


    /**
     *
     *Springboot注入RestTemplate类，追踪RestTemplate 实例化过程发现默认的RestTemplate 只支持application/json格式，
     * 所以需要手动补充text/html格式
     *

     *
     * @return
     */
    @Bean()
    public RestTemplate restTemplate2(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        return restTemplate;
    }



    /**
     * 使用HttpClient作为底层客户端
     * @return
     */
//    private ClientHttpRequestFactory getClientHttpRequestFactory() {
//        int timeout = 5000;
//        RequestConfig config = RequestConfig.custom()
//                .setConnectTimeout(timeout)
//                .setConnectionRequestTimeout(timeout)
//                .setSocketTimeout(timeout)
//                .build();
//        CloseableHttpClient client = HttpClientBuilder
//                .create()
//                .setDefaultRequestConfig(config)
//                .build();
//        return new HttpComponentsClientHttpRequestFactory(client);
//    }



    /**
     * 使用OkHttpClient作为底层客户端
     *
     *
     * OkHttp 优于 Apache的HttpClient、Apache的HttpClient优于HttpURLConnection。
     *
     *
     *
     * @return
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }


    /**
     * 们使用了 SimpleClientHttpRequestFactory，它是 RestTemplate 的默认请求工厂，用来包装 HttpURLConnection。
     * 通过设置 setConnectTimeout 和 setReadTimeout 方法，我们可以分别为连接和读取操作设置超时时间
     * @return
     */
    private ClientHttpRequestFactory getDefalutClientHttpRequestFactory(){
        // 创建SimpleClientHttpRequestFactory的实例
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置连接超时时间（单位：毫秒）
        requestFactory.setConnectTimeout(5000); // 5秒

        // 设置读取超时时间（单位：毫秒）
        requestFactory.setReadTimeout(5000); // 5秒
        return requestFactory;
    }


    /**
     * 定义拦截器
     * @return
     */
    @Bean
    public RestTemplateInterceptor restTemplateInterceptor() {
        return new RestTemplateInterceptor();
    }



}
