package com.lzl.jsp.pk.service;

import com.lzl.jsp.pk.pojo.LessonCustom;

import java.util.List;


public interface LessonService {
	 public void addLesson(LessonCustom lessonCustom)throws Exception;
	//查看所有课程
	public List<LessonCustom> selectLesson() throws Exception;
}
