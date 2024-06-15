package com.lzl.jsp.pk.controller;

import com.lzl.jsp.pk.pojo.TeacherCustom;
import com.lzl.jsp.pk.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class TeacherController {
 
	 @Autowired
	 private TeacherService teacherService;
	 /**
	  * 
	  *方法的作用：添加
	  *方法名：addTeacher
	  *创建时间：2018年5月17日-下午4:18:13
	  *@author LZL
	  *@return String
	  * @param teacherCustom
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/addTeacher")
	 public String addTeacher(TeacherCustom teacherCustom)throws Exception{
		 System.out.println("教师姓名为："+teacherCustom.getTeachername());
		 teacherService.insertTeacher(teacherCustom);
		 return "addTeacher_result";
	 }
	 
}
