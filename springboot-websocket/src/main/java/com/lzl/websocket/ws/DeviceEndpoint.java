package com.lzl.websocket.ws;


import com.lzl.websocket.config.SpringConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eren.liao
 * @version v1.0
 * @description   参考 https://blog.csdn.net/2509_92429939/article/details/153966281
 * @date 2025/11/3 16:47
 **/
@Slf4j
@Component
@Scope("prototype")
// 确保每个设备只有一个 DeviceEndpoint 实例
@ServerEndpoint(value = "/pda", configurator = SpringConfigurator.class)
public class DeviceEndpoint {

    @Resource
    private ApplicationContext applicationContext;

    public static final ConcurrentHashMap<String, Session> deviceMap = new ConcurrentHashMap<>();
    private String deviceId="";


    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        deviceId = (String) config.getUserProperties().get("connection");
        deviceMap.put(deviceId, session);
        log.info("设备连接：{}", deviceId);
    }
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("接收到设备数据：{}", message);

        // 没有注入 所以只是伪代码
        applicationContext.getBean("amqpTemplate");
        // 发消息队列
//            session.getBasicRemote().sendText("数据处理成功");
    }
    @OnClose
    public void onClose(Session session) {
        String deviceId = (String) session.getUserProperties().get("connection");
        if (deviceId != null && !deviceId.isEmpty()) {
            deviceMap.remove(deviceId);
            log.info("设备断开连接：{}", deviceId);
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket error for session {}: {}", session.getId(), error.getMessage(), error);
    }
}


    
    
    