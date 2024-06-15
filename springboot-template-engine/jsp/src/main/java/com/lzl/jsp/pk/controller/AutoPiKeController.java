package com.lzl.jsp.pk.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzl.jsp.pk.pojo.ClassRoomCustom;
import com.lzl.jsp.pk.pojo.ClazzCustom;
import com.lzl.jsp.pk.pojo.TPContentCustom;
import com.lzl.jsp.pk.pojo.TeachPlanCustom;
import com.lzl.jsp.pk.service.ClassRoomService;
import com.lzl.jsp.pk.service.ClazzService;
import com.lzl.jsp.pk.service.TPContentService;
import com.lzl.jsp.pk.service.TeachPlanService;
import com.lzl.jsp.pk.util.ReturnRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


/**
 * 
 *类的功能：排课
 *类名AutoPiKeController
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月4日-下午1:28:43
 */
@Controller
public class AutoPiKeController {
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private ClassRoomService classRoomService;
	@Autowired
	private TeachPlanService teachPlanService;
	@Autowired
	private TPContentService tpContentService;
	 /**
	  * 
	  *方法的作用：点击进入开始排课列表
	  *方法名：clickAutoPiKe
	  *创建时间：2018年5月4日-下午1:29:25
	  *@author LZL
	  *@return void
	  */
	@RequestMapping("/enterAutoPiKe")
	 public void enterAutoPiKe(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 List<ClazzCustom> clazzList = clazzService.selectClazz();
		 request.setAttribute("clazzList", clazzList);
		 request.getRequestDispatcher("jsp/time_anpai.jsp").forward(request, response);
	 }
	/**
	 * 
	 *方法的作用：点击开始排课按钮
	 *方法名：clickAutoPiKe
	 *创建时间：2018年5月4日-下午1:38:52
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/clickAutoPiKe")
    public String clickAutoPiKe(String clazz_name)throws Exception{
    	/*    思路
    	 1.根据班级name查出班级人数
    	 2.根据班级人数查出符合条件的上课教室
    	 3.自定义随机函数随机选出一个教室上课
    	 4.根据班级name查出该班级上课计划
    	 5. 课程名
    	 	教师名
    	 	起始周到结束周
    	 	教室名
    	*/
		int num = clazzService.findPresonByName(clazz_name);
    	List<ClassRoomCustom> roomList = classRoomService.findClassRoomByNum(num);
    	List<TeachPlanCustom> teachPlanList = teachPlanService.findTeachPlanByName(clazz_name);
    	for(TeachPlanCustom teachPlan : teachPlanList){
    		if(teachPlan.getClassroom_name().equals("")){
    			int random = ReturnRandom.returnRandom(roomList.size());
    	    	String roomname = roomList.get(random).getRoomname();
    	    	System.out.println("随机的班级"+roomname);
    			teachPlan.setClassroom_name(roomname);
        		teachPlanService.updateTeachPlanByid(teachPlan);
    		}
    	}
    	return JSONObject.toJSON(teachPlanList).toString();
}
	
	/**
	 * 
	 *方法的作用：查询一周的总课时
	 *方法名：findZks
	 *创建时间：2018年5月12日-下午12:25:15
	 *@author LZL
	 *@return String
	 * @param clazz_name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findZks")
	public int findZks(String clazz_name)throws Exception{
		int sum=0;
		List<TeachPlanCustom> teachPlanList = teachPlanService.findTeachPlanByName(clazz_name);
		for(TeachPlanCustom teachPlan : teachPlanList){
			   int i = teachPlan.getZks();
			   sum=sum+i;
		}
		return sum;
	}
	/**
	 * 
	 *方法的作用：保存排课内容
	 *方法名：savePiKeContent
	 *创建时间：2018年5月13日-上午11:36:50
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/savePiKeContent")
	public String savePiKeContent(TPContentCustom tpContentCustom)throws Exception{
		tpContentService.savePiKeContent(tpContentCustom);
		return"success";
	}
}
