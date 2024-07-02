package com.lzl.rabbitmq.comsumer;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

/**
 * @author lzl
 *
 * 当消息回滚到消息队列时，这条消息不会回到队列尾部，而是仍是在队列头部，
 * 这时消费者会又接收到这条消息，如果想消息进入队尾，须确认消息后再次发送消息。
 * channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
 *                     message.getMessageProperties().getReceivedRoutingKey(),
 *                     MessageProperties.PERSISTENT_TEXT_PLAIN,
 *                     message.getBody());
 *
 * 但是往往正常来说一次有异常，就一直都有异常
 */
@Component
@Slf4j
public class Comsumer3 {

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "comsumer3", type = ExchangeTypes.DIRECT),
                    key = "auto-ack-queue-key",
                    value = @Queue(value = "auto-ack-queue", autoDelete = "true")
            ))
    public void autoAck(String messge) throws InterruptedException {
        log.info("receive: {} 开始消费",messge);
        // 模拟执行任务
        Thread.sleep(1000);
        log.info("消费完成");
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "comsumer3", type = ExchangeTypes.DIRECT),
                    key = "manual-ack-queue-key",
                    value = @Queue(value = "manual-ack-queue", autoDelete = "true")
            ), ackMode = "MANUAL")
    public void manualAck(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("receive: {} 开始消费",msg);
        try {
            Thread.sleep(1000);
            // 采用手动应答模式，注意设置了手动模式，就必须ACK。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                // 手动确认消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
        log.info("消费完成");
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "comsumer3", type = ExchangeTypes.DIRECT),
                    key = "customContainerFactory-key",
                    value = @Queue(value = "customContainerFactory-queue", autoDelete = "true")
            ), containerFactory = "listenerContainerFactory")
    public void customContainerFactory(String messge) throws InterruptedException {
        log.info("receive: {} 开始消费",messge);
        // 模拟执行任务
        Thread.sleep(1000);
        log.info("消费完成");
    }
}
