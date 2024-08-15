package com.lzl.rabbitmq.comsumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class Comsumer4 {


//    @RabbitListener(
//            bindings = @QueueBinding(
//                    exchange = @Exchange(value = "testExchange"),
//                    key = "test-consumer-timeout",
//                    value = @Queue(value = "test-consumer-timeout-mq")
//            ))
//    public void process(Message message, Channel channel, String s) throws IOException, InterruptedException {
//        log.info(s);
//        log.info("receive: {} 开始消费模拟消费超时队列" , new String(message.getBody()));
//        /*
//         ... 业务操作
//         */
//        TimeUnit.MINUTES.sleep(40);
//        // 采用手动应答模式，注意设置了手动模式，就必须ACK。
//        log.info("消费完成");
//
//    }


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "testExchange"),
                    key = "test-consumer-timeout2",
                    value = @Queue(value = "test-consumer-timeout-mq2",autoDelete = "true")
            ))
    public void process2(Message message, Channel channel, String s) throws IOException, InterruptedException {
        log.info(s);
        log.info("receive: {} 开始消费模拟消费超时队列" , new String(message.getBody()));
        try {
        /*
         ... 业务操作
         */
            TimeUnit.HOURS.sleep(24);
        }catch (Exception e){
            log.error("消费异常");
        }
        log.info("消费完成");
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "testExchange"),
                    key = "test-consumer-timeout3",
                    value = @Queue(value = "test-consumer-timeout-mq3")
            ))
    public void process3(Message message, Channel channel, String s) throws IOException, InterruptedException {
        log.info(s);
        log.info("receive: {} 开始消费模拟消费超时队列" , new String(message.getBody()));
        /*
         ... 业务操作
         */
        TimeUnit.HOURS.sleep(24);
        // 采用手动应答模式，注意设置了手动模式，就必须ACK。
        log.info("消费完成");

    }

}
