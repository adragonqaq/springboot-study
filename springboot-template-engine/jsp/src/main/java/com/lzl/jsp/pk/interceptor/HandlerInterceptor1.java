package com.lzl.jsp.pk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 *类的功能：拦截器
 *类名HandlerInterceptor1
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月27日-上午9:26:17
 */
public class HandlerInterceptor1 implements HandlerInterceptor{
 
	/**
	 * handler完成后执行此方法
	 * 用于，统一异常处理，统一日志处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 进入handler方法之后，返回mav之前执行
	 * 应用场景从mav出发：将公共的模型数据（比如菜单导航）在这里传到试图，也可以在这里统一视图
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 进入handler方法之前执行
	 * 用于身份认证，身份授权
	 * 比如身份认证，如果认证通过表示当前用户没有登陆，需要拦截此方法不再向下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//false表示拦截，不再向下执行
		//ture表示放行
		return false;
	}

}
