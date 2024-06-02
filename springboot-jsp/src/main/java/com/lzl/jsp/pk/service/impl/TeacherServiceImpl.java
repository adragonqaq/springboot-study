package com.lzl.jsp.pk.service.impl;


import java.util.List;

import com.lzl.jsp.pk.mapper.TeacherMpper;
import com.lzl.jsp.pk.pojo.TeacherCustom;
import com.lzl.jsp.pk.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

	 @Autowired
	 private TeacherMpper teacherMpper;
	 
	@Override
	public void insertTeacher(TeacherCustom teacherCustom) throws Exception {
		// TODO Auto-generated method stub
		teacherMpper.insertTeacher(teacherCustom);
	}

	@Override
	public List<TeacherCustom> selectTeacher() throws Exception {
		// TODO Auto-generated method stub
		return teacherMpper.selectTeacher();
	}

}
