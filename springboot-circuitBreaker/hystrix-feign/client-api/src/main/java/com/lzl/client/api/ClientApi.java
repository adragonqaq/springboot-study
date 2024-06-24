package com.lzl.client.api;


import com.lzl.client.fallback.ClientApiImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "hystrix-feing-client",fallback = ClientApiImpl.class)
public interface ClientApi {


    @GetMapping("/client/sayHello")
    String sayHello();


}
