package com.lzl.websocket.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/11/3 16:43
 **/
@Component
public class SpringConfigurator extends ServerEndpointConfig.Configurator {

    private static ApplicationContext applicationContext;

    /**、
     * 这段代码的作用是将Spring的ApplicationContext注入到SpringConfigurator类中，以便在WebSocket端点中实现依赖注入。
     * 具体来说，这段代码通过Spring的@Autowired注解将Spring上下文（ApplicationContext）注入到SpringConfigurator类的静态字段中。
     * @param applicationContext
     */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringConfigurator.applicationContext = applicationContext;
    }

    /**
     * @doc
     * 为什么写了这个类就可以在 WebSocket 端点中进行注入数据？
     * 在 WebSocket 端点中，您可以使用 Spring 的 @Autowired 注解注入 Spring 管理的 Bean，因为 SpringConfigurator 类配置了 getEndpointInstance
     * 方法来从 Spring 上下文中获取 WebSocket 端点实例。这允许您在 WebSocket 端点中利用 Spring 的依赖注入和管理特性。
     * @param clazz
     * @return
     * @param <T>
     * @throws InstantiationException
     */
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return applicationContext.getBean(clazz);
    }
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
//        super.modifyHandshake(sec, request, response);

//        将从握手的请求中获取httpsession
        String device_id = request.getParameterMap().get("device_id").get(0);


        /**
         * 一般会在请求头中添加token 解析出来id作为键值对
         */
        java.util.Map<String, Object> properties =  sec.getUserProperties();
        /**
         */
        properties.put("connection","Bear" +device_id);
    }
}

    
    
    