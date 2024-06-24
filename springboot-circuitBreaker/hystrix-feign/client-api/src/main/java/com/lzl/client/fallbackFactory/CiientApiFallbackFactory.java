package com.lzl.client.fallbackFactory;

import com.lzl.client.api.ClientApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CiientApiFallbackFactory implements FallbackFactory<ClientApi> {
    @Override
    public ClientApi create(Throwable cause) {

        log.error("异常:{}", cause.getMessage(), cause);
        return new ClientApi() {
            @Override
            public String sayHello() {
                return "byebye";
            }
        };
    }
}
