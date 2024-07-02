package com.lzl.rabbitmq.producter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String msg = "hello!";
        //这种方式发送消息默认是持久化消息
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("123");
//        rabbitTemplate.convertAndSend("testExchange","test-routingKey",msg,correlationData);
        amqpTemplate.convertAndSend("testExchange","test-routingKey",msg);
    }


    public void send2(){
        String msg = "retry!";
        //这种方式发送消息默认是持久化消息
//        rabbitTemplate.convertAndSend("testExchange","retry-routingKey",msg);
        amqpTemplate.convertAndSend("testExchange","retry-routingKey",msg);
    }
}
