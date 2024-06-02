package com.lzl.jsp.pk.service.impl;

import java.util.List;

import com.lzl.jsp.pk.mapper.ClassRoomMapper;
import com.lzl.jsp.pk.pojo.ClassRoomCustom;
import com.lzl.jsp.pk.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {
 
	 @Autowired
	private ClassRoomMapper classRoomMapper;
	
	@Override
	public void addClassRoom(ClassRoomCustom classRoomCustom) throws Exception {
		 classRoomMapper.addClassRoom(classRoomCustom);
	}

	@Override
	public List<ClassRoomCustom> selectClassRoom() throws Exception {
		// TODO Auto-generated method stub
		return classRoomMapper.selectClassRoom();
	}

	@Override
	public List<ClassRoomCustom> findClassRoomByNum(int num) throws Exception {
		// TODO Auto-generated method stub
		return classRoomMapper.findClassRoomByNum(num);
	}

}
