package com.lzl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 消费者链路日志切面
 * @author eren.liao
 * @date 2021/12/4 14:22
 */
@Aspect
@Component
@Slf4j
public class RabbitmqTraceIdAspect {

    /**
     * 设置切入点
     */
    @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public void RabbitmqTraceIdPoinCut() {
    }


    /**
     * 环绕
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("RabbitmqTraceIdPoinCut()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        try {
            MDC.put("trace-id","11");
            log.info("消息监听重刷完成,trace-id:{}","11");
        } catch (Exception e) {
            log.error("链路日志切面，重置trace-id出错!", e);
        }
        //执行业务
        Object result = point.proceed();
        return result;
    }
}
