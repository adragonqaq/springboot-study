package com.lzl.jsp.pk.controller;

import com.lzl.jsp.pk.exception.MysqlException;
import com.lzl.jsp.pk.pojo.ClassRoomCustom;
import com.lzl.jsp.pk.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ClassRoomController {
	
	
	@Autowired
	 private ClassRoomService classRoomService;
	/**
	 * 
	 *方法的作用：添加教室
	 *方法名：addClassRoom
	 *创建时间：2018年5月17日-下午4:16:30
	 *@author LZL
	 *@return String
	 * @param classRoomCustom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addClassRoom")
	public String addClassRoom(ClassRoomCustom classRoomCustom)throws Exception{
		String roomname=classRoomCustom.getRoomname();
	//	int accptnum=classRoomCustom.getAccptnum();
		String category=classRoomCustom.getCategory();
		if(roomname==""||category==""){
			throw new MysqlException("输入信息不完全");
		}
		System.out.println(classRoomCustom.getAccptnum());
		classRoomService.addClassRoom(classRoomCustom);
		return "add_result";
	}
}
