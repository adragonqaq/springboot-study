package com.lzl.client.fallback;

import com.lzl.client.api.ClientApi;
import org.springframework.stereotype.Component;


/**
 * 必须要注入到spring容器中，否则启动报错
 */
@Component
public class ClientApiImpl implements ClientApi {
    @Override
    public String sayHello() {
        return "byebye";
    }
}
