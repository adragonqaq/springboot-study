package com.lzl.jsp.pk.service.impl;


import java.util.List;

import com.lzl.jsp.pk.mapper.LessonMapper;
import com.lzl.jsp.pk.pojo.LessonCustom;
import com.lzl.jsp.pk.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LessonServiceImpl implements LessonService {
 
	 @Autowired
	 private LessonMapper lessonMapper;
	 
	@Override
	public void addLesson(LessonCustom lessonCustom) throws Exception {
		// TODO Auto-generated method stub
		lessonMapper.addLesson(lessonCustom);
	}

	@Override
	public List<LessonCustom> selectLesson() throws Exception {
		// TODO Auto-generated method stub
		return lessonMapper.selectLesson();
	}

	

}
