package com.lzl.jsp.pk.service;

import com.lzl.jsp.pk.pojo.TeacherCustom;

import java.util.List;


public interface TeacherService {
	 public void insertTeacher(TeacherCustom teacherCustom)throws Exception;
	//查看所有课程
	public List<TeacherCustom> selectTeacher() throws Exception;
}
