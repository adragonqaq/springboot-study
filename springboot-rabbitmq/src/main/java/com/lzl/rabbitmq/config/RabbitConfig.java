package com.lzl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class RabbitConfig {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
      * 定义一个hello的队列
      * Queue 可以有4个参数
      *      1.队列名
      *      2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
      *      3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
      *      4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
      */
    @Bean
    public Queue helloQueue(){
        return new Queue("test");
    }

    /**
     *
     * @return
     */
    @Bean
    public DirectExchange helloExchange(){
        return new DirectExchange("testExchange", true, false);
    }

    @Bean
    public Binding binding(Queue helloQueue, DirectExchange helloExchange){
        return BindingBuilder.bind(helloQueue).to(helloExchange).with("queue-routingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){

        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                log.info("消息发送成功 id:{}",correlationData.getId());
            }else {
                log.error("消息发送失败，原因：{}", cause);
            }
        });
        return rabbitTemplate;
    }

}
