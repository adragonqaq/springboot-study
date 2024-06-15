package com.lzl.jsp.pk.mapper;

import com.lzl.jsp.pk.pojo.LessonCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LessonMapper {
	//添加课程
	 public void addLesson(LessonCustom lessonCustom)throws Exception;
	 //查看所有课程
	public List<LessonCustom> selectLesson() throws Exception;
}
