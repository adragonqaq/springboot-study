package com.lzl.rabbitmq.producter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lzl
 */
@Component
public class Producter {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String msg = "hello!";
        //这种方式发送消息默认是持久化消息
        rabbitTemplate.convertAndSend("testExchange","queue-routingKey",msg);
    }
}
