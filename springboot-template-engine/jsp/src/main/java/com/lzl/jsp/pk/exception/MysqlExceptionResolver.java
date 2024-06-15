package com.lzl.jsp.pk.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *类的功能：全局异常处理器
 *类名MysqlExceptionResolver
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月27日-上午8:48:55
 */
public class MysqlExceptionResolver implements HandlerExceptionResolver{
	/*handler就是处理器适配器要执行的handler对象（只有method）
	 * ex 就是系统抛出的异常
	 * */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		//解析异常类型
		//如果该异常类型是系统自定义的异常，直接取出异常信息，在错误页面展示
		String message=null;
		if(ex instanceof MysqlException){
			message=((MysqlException) ex).getMassage();
		}else{
			//如果该异常类型不是系统自定义的异常，构造一个自定义的异常类型
			message="未知错误";
		}
			ModelAndView modelAndView =new ModelAndView();
			modelAndView.addObject("message", message);
			modelAndView.setViewName("error");
			return modelAndView;
	}

}
