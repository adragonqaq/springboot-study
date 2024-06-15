package com.lzl.restTemplate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringcloudConfig {


    /**
     *
     * @LoadBalanced 是 Netflix 的 ribbon 中的一个负载均衡的注解，并完成以下工作：
     *
     * 从负载均衡器中选一个对应的服务实例，所有的服务名实例都放在负载均衡器中的serverlist中；
     * 从挑选的实例中去请求内容；
     * 由服务名转为真正使用的ip地址；
     *
     *通过源码跟踪可知，restTemplate能通过服务名获取到具体的服务，是由LoadBalancerInterceptor这个拦截器实现的，而具体的工作是由RibbonLoadBalancerClient来完成的。
     * RibbonLoadBalancerClient将服务名通过负载均衡策略转为了实际的ip和端口后再apply给restTemplate。
     *
     *即可通过 http://SERVICE-NAME/quest-path 访问对应名称的微服务
     *
     *这种方法需要服务被注册到服务发现组件（如Eureka，Nacos，Consul等）。这样，应用才能找到服务名对应的实际网络位置。
     *
     * @return
     */
//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        // 设置超时
//        requestFactory.setConnectTimeout(60 * 1000);
//        requestFactory.setReadTimeout(60 * 1000);
//        //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        return restTemplate ;
//    }


}
