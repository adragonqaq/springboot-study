package com.lzl.rabbitmq.comsumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Comsumer {

    @RabbitListener(queues = "test")
    public void process(Message message, Channel channel) throws IOException {
        // 采用手动应答模式
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        /**
         * basicACK NACK  reject这些有什么区别和作用？
         */
        log.info("receive: " + new String(message.getBody()));
    }


}
