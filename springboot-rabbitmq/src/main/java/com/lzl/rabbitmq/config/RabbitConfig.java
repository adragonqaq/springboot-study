package com.lzl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author lzl
 */
@Configuration
@Slf4j
public class RabbitConfig {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
      * 定义一个test的队列
      * Queue 可以有4个参数
      *      1.name 队列名
      *      2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
      *      3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
      *      4.exclusive     设置陪它表示该消息队列是否只在当前connection生效,默认是false
     *       5.arguments    设置队列的其他参数，比如设置为死信队列
      */
    @Bean
    public Queue testQueue(){
        return new Queue("test",true);
    }

    /**
     * Exchange有以下几个参数
     *  name: 交换机名称
     *  type: 交换机类型，分别有 direct, topic, fanout, headers
     *  durable: 持久化交换机，重启时不会丢失相关信息
     *  autoDelete: 和这个交换机绑定的队列解绑，会自动删除此交换机
     *
     *
     * @return
     */
    @Bean
    public DirectExchange testExchange(){
        return new DirectExchange("testExchange", true, false);
    }

    @Bean
    public Binding binding(Queue testQueue, DirectExchange testExchange){
        return BindingBuilder.bind(testQueue).to(testExchange).with("queue-routingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){

        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        /**
         * Mandatory：
         * 为true时,消息通过交换器无法匹配到队列会返回给生产者 并触发MessageReturn
         * 为false时,匹配不到会直接被丢弃
         */
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        /**
         * return机制，用于处理一些不可路由的消息，在一些特殊的情况下，当前的exchange不存在或者指定的路由key路由不到，这时如果我们需要及时监听这种消息
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        /**
         * confirm机制，消息的确认，是指生产者投递消息之后，如果Broker收到消息，则会给生产者一个应答，生产者能接收应答，用来确定这条消息是否正常的发送到Broker，这种机制是消息可靠性投递的核心保障。
         * confirm机制是只保证消息到达exchange，并不保证消息可以路由到正确的queue。
         */
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
