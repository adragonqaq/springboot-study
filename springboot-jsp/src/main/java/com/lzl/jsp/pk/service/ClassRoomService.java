package com.lzl.jsp.pk.service;


import com.lzl.jsp.pk.pojo.ClassRoomCustom;

import java.util.List;


public interface ClassRoomService {
	// 添加教室信息
	public void addClassRoom(ClassRoomCustom classRoomCustom) throws Exception;
	// 查看所有教室信息
	public List<ClassRoomCustom> selectClassRoom() throws Exception;
	//根据人数查询教室
	public List<ClassRoomCustom> findClassRoomByNum(int num)throws Exception;
}
