package com.lzl.rabbitmq.comsumer;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class Comsumer2 {


    /**
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "retryQueue")
    public void process(Message message, Channel channel) throws IOException {
        String receiveMes = new String(message.getBody());
        log.info("receive: {} 开始消费",receiveMes);
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            int i = 1/0;
            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费完成");
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收！");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列，否则就丢弃
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } else {
                log.error("消息即将再次返回队列处理！");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
