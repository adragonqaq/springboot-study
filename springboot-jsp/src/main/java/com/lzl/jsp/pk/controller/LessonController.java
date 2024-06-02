package com.lzl.jsp.pk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lzl.jsp.pk.pojo.LessonCustom;
import com.lzl.jsp.pk.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LessonController {

	 @Autowired
	 private LessonService lessonService;
	 /**
	  * 
	  *方法的作用：添加
	  *方法名：addLesson
	  *创建时间：2018年5月17日-下午4:17:48
	  *@author LZL
	  *@return String
	  * @param lessonCustom
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/addLesson")
	 public String addLesson(LessonCustom lessonCustom)throws Exception{
		 lessonService.addLesson(lessonCustom);
		 return "addLesson_result";
	 }
	 
	 //没用
	 public String selectLesson(HttpServletRequest request)throws Exception{
		 List<LessonCustom> lessonList = lessonService.selectLesson();
		 request.setAttribute("lessonList", lessonList);
		 return "";
		 
		 
	 }
}
