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

import com.alibaba.fastjson.JSONObject;

/**
 * 
 *类的功能：手动调整课表
 *类名ShouDongPiKeController
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月13日-上午10:19:19
 */
@Controller
public class ShouDongPiKeController {
	@Autowired
	private ClazzService clazzService;
	@Autowired 
	private TPContentService tpContentService;
	/**
	 * 
	 *方法的作用：进入
	 *方法名：enterShouDongPiKe
	 *创建时间：2018年5月17日-下午4:18:01
	 *@author LZL
	 *@return void
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/enterShouDongPiKe")
	 public void enterShouDongPiKe(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 List<ClazzCustom> clazzList = clazzService.selectClazz();
		 request.setAttribute("clazzList", clazzList);
		 request.getRequestDispatcher("jsp/shoudong.jsp").forward(request, response);
	 }
	/**
	 * 
	 *方法的作用：显示排课信息根据班级名称
	 *方法名：showPiKeContent
	 *创建时间：2018年5月13日-上午11:35:21
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/showPiKeByClazzName")
	public String showPiKeByClazzName(String clazz_name)throws Exception{
		TPContentCustom tpContentCustom = tpContentService.showPiKeByClazzName(clazz_name);
		/*JSONObject json=new JSONObject();
		json.put("tpcontent", JSONObject.toJSON(tpContentCustom));*/
		return tpContentCustom.getTeachplan_content();
	}
	/**
	 * 
	 *方法的作用：手动调整课表并保存
	 *方法名：updatePiKeByClazzName
	 *创建时间：2018年5月14日-下午12:47:30
	 *@author LZL
	 *@return String
	 * @param tpContentCustom
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updatePiKeByClazzName")
	public String updatePiKeByClazzName(TPContentCustom tpContentCustom)throws Exception{
		tpContentService.updatePiKeByClazzName(tpContentCustom);
		return "ok";
	}
}
