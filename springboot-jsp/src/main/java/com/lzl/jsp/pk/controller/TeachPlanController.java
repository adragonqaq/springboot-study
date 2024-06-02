package com.lzl.jsp.pk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzl.jsp.pk.pojo.*;
import com.lzl.jsp.pk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TeachPlanController {

	@Autowired
	private TeachPlanService teachPlanService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	 private ClassRoomService classRoomService;
/**
 * 
 *方法的作用：添加教学计划
 *方法名：addTeachPlan
 *创建时间：2018年5月2日-下午4:28:48
 *@author LZL
 *@return void
 * @param teachPlanCustom
 * @param request
 * @param response
 * @throws Exception
 */
	@RequestMapping("/addTeachPlan")
	public String addTeachPlan(TeachPlanCustom teachPlanCustom,
							   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.println("123="+teachPlanCustom.getClazz_id());
		teachPlanService.addTeachPlan(teachPlanCustom); 
		return "addTeachPlan_result";
	}
/**
 * 
 *方法的作用：显示添加的基本信息
 *方法名：showItems
 *创建时间：2018年5月2日-下午4:29:23
 *@author LZL
 *@return void
 * @param request
 * @param response
 * @throws Exception
 */
	@RequestMapping("/showItems")
	public void showItems(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<LessonCustom> lessonList = lessonService.selectLesson();
		request.setAttribute("lessonList", lessonList);
		List<TeacherCustom> teacherLsit = teacherService.selectTeacher();
		request.setAttribute("teacherLsit", teacherLsit);
		List<ClazzCustom> clazzList = clazzService.selectClazz();
		request.setAttribute("clazzList", clazzList);
		List<ClassRoomCustom> classRoomList = classRoomService.selectClassRoom();
		// 分发转向
		request.getRequestDispatcher("jsp/teachplan.jsp").forward(request,
				response);
	}
}
