package com.lzl.rabbitmq.producter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producter {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String msg = "hello!";
        rabbitTemplate.convertAndSend("testExchange","queue-routingKey",msg);
    }
}
