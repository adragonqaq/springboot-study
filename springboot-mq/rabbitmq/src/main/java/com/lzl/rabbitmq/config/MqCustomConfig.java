package com.lzl.rabbitmq.config;


import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;


/**
 *
 *
 * https://blog.csdn.net/weixin_51407397/article/details/131358848
 *
 */
@Configuration
public class MqCustomConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setAdviceChain(retries());
        return factory;
    }

    @Bean
    public RetryOperationsInterceptor retries() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5) //设置最大尝试次数为5（不重试）
                .backOffOptions(1000, 5.0, 10000)
                .recoverer(new RejectAndDontRequeueRecoverer()).build();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}
