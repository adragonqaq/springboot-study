package com.lzl.restTemplate.config;

import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    /**
     *
     * 能使用MDC保存traceId等参数的根本原因是，
     * 用户请求到应用服务器，Tomcat会从线程池中分配一个线程去处理该请求。
     * 那么该请求的整个过程中，保存到MDC的ThreadLocal中的参数，
     * 也是该线程独享的，所以不会有线程安全问题
     *
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("TRACE_ID", UUID.randomUUID().toString());
        System.out.println("记录请求日志");
        chain.doFilter(request, response);
        System.out.println("记录响应日志");
    }

    @Override
    public void destroy() {
    }
}
