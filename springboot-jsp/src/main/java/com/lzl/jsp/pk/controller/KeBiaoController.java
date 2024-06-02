package com.lzl.jsp.pk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzl.jsp.pk.pojo.ClazzCustom;
import com.lzl.jsp.pk.pojo.TPContentCustom;
import com.lzl.jsp.pk.service.ClazzService;
import com.lzl.jsp.pk.service.TPContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class KeBiaoController {

	@Autowired
	private ClazzService clazzService;
	
	@Autowired
	private TPContentService tpContentService;
	/**
	 * 
	 *方法的作用：进入查看课表
	 *方法名：enterShouDongPiKe
	 *创建时间：2018年5月14日-下午1:28:53
	 *@author LZL
	 *@return void
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/enterShowKeBiao")
	 public void enterShouDongPiKe(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 List<ClazzCustom> clazzList = clazzService.selectClazz();
		 request.setAttribute("clazzList", clazzList);
		 request.getRequestDispatcher("jsp/showkebiao.jsp").forward(request, response);
	 }
	/**
	 * 
	 *方法的作用：显示课表根据班级名称
	 *方法名：showKeBiaoByClazzName
	 *创建时间：2018年5月14日-下午1:36:18
	 *@author LZL
	 *@return String
	 * @param clazz_name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/showKeBiaoByClazzName")
	public String showKeBiaoByClazzName(String clazz_name)throws Exception{
		TPContentCustom tpContentCustom = tpContentService.showPiKeByClazzName(clazz_name);
		return tpContentCustom.getTeachplan_content();
	}
	/**
	 * 
	 *方法的作用：下载课表
	 *方法名：downloadKeBiao
	 *创建时间：2018年5月17日-下午4:13:07
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@RequestMapping("/downloadKeBiao")
	public void downloadKeBiao()throws Exception{
		
		
		
	}
}
