package com.lzl.jsp.pk.controller;

import com.lzl.jsp.pk.pojo.ClazzCustom;
import com.lzl.jsp.pk.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ClazzController {

	@Autowired
	private ClazzService clazzService;
	/**
	 * 
	 *方法的作用：添加班级信息
	 *方法名：addClazz
	 *创建时间：2018年5月17日-下午4:17:05
	 *@author LZL
	 *@return String
	 * @param clazzCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addClazz")
	 public String addClazz(ClazzCustom clazzCustom)throws Exception{
		 clazzService.addClass(clazzCustom);
		 return "addClass_result";
	 }
}
