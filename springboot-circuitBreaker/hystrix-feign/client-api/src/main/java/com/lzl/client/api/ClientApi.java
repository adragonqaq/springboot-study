package com.lzl.client.api;


import com.lzl.client.fallback.ClientApiImpl;
import com.lzl.client.fallbackFactory.CiientApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = "hystrix-feing-client",fallback = ClientApiImpl.class)
@FeignClient(value = "hystrix-feing-client",fallbackFactory = CiientApiFallbackFactory.class)
public interface ClientApi {


    @GetMapping("/client/sayHello")
    String sayHello();


}
