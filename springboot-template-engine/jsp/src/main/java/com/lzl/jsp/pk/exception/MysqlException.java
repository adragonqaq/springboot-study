package com.lzl.jsp.pk.exception;
/**
 * springmvc提供全局异常处理器（一个系统就一个异常处理器）进行统一异常处理
 *类的功能：自定义异常--数据库异常
 *类名MysqlException
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月27日-上午8:41:43
 */
public class MysqlException extends Exception{

		 //异常信息
		public String massage;
		
		public MysqlException(String massage){
			super(massage);
			this.massage=massage;
		}

		public String getMassage() {
			return massage;
		}

		public void setMassage(String massage) {
			this.massage = massage;
		}
		
}
