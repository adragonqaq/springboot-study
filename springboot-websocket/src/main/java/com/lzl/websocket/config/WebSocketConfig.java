package com.lzl.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    /**
     * 在Spring Boot中，ServerEndpointExporter是一个非常重要的Bean，
     * 因为它负责扫描和注册所有带有@ServerEndpoint注解的WebSocket端点。
     *
     * 以下是该Bean的作用及其配置的详细说明：
     *
     * 作用
     * 扫描带有@ServerEndpoint注解的类：ServerEndpointExporter会自动扫描应用程序中的所有@ServerEndpoint注解的类，并将它们注册为WebSocket端点。
     * 注册WebSocket端点：它将找到的WebSocket端点注册到默认的容器（如Tomcat、Jetty、Undertow等），使这些端点能够接受和处理WebSocket连接和消息。
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
