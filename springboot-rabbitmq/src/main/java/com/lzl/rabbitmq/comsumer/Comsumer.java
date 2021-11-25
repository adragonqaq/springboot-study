package com.lzl.rabbitmq.comsumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lzl
 */
@Component
@Slf4j
public class Comsumer {

    @RabbitListener(queues = "test")
    public void process(Message message, Channel channel) throws IOException {

        log.info("receive: " + new String(message.getBody()));
        try {
            /*
             ... 业务操作
             */
            // 采用手动应答模式，注意设置了手动模式，就必须ACK。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            //表示拒绝消费，此消息重新回到队列终
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
        /**
         *channel.basicReject(deliverTay, true);
         *basic.reject方法拒绝deliveryTag对应的消息，第二个参数是否requeue，true则重新入队列，否则丢弃或者进入死信队列。
         * 该方法reject后，该消费者还是会消费到该条被reject的消息。
         *
         *channel.basicNack(deliverTay,false,true);
         *basic.nack方法为不确认deliveryTag对应的消息，第二个参数是否应用于多消息，第三个参数是否requeue，与basic.reject区别就是同时支持多个消息，可以nack该消费者先前接收未ack的所有消息。nack后的消息也会被自己消费到。
         *
         *channel.basicRecover(true);
         * basic.recover是否恢复消息到队列，参数是是否requeue，true则重新入队列，并且尽可能的将之前recover的消息投递给其他消费者消费，而不是自己再次消费。false则消息会重新被投递给自己。
         *
         * channel.basicAck(deliverTay, true);
         * 手动确认消息被消费，如果设置为true，一条消息应答了，那么之前的全部消息将被应答。
         * 比如目前channel中有delivery tags为5,6,7,8的消息，那么一旦8被应答，那么5，6，7将都被应答，如果设置为false，那么5，6，7将不会被应答。（不建议设置，毕竟一个channel中可能会有多个consumer）
         */

    }


}
