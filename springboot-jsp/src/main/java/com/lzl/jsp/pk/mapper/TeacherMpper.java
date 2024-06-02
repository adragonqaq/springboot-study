package com.lzl.jsp.pk.mapper;

import com.lzl.jsp.pk.pojo.TeacherCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TeacherMpper {
     //添加教师
	 public void insertTeacher(TeacherCustom teacherCustom)throws Exception;
	//查看所有课程
	public List<TeacherCustom> selectTeacher() throws Exception;
}
