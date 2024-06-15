package com.lzl.jsp.pk.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lzl.jsp.pk.pojo.User;
import com.lzl.jsp.pk.pojo.UserCustom;
import com.lzl.jsp.pk.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


/***
 * 
 *类的功能：
 *类名UserController
 * @author LZL
 * @version 1.0.0
 *时间 2018年4月17日-下午12:58:43
 */
@Controller
public class UserController {
 
	 @Autowired
	private UserService userService;
	 /**
	  * 
	  *方法的作用：用户登陆
	  *方法名：userLogin
	  *创建时间：2018年5月17日-下午4:19:25
	  *@author LZL
	  *@return void
	  * @param model
	  * @param userCustom
	  * @param request
	  * @param veryCode
	  * @param response
	  * @throws Exception
	  */
	@RequestMapping("/userLogin")
	 public void userLogin(Model model, UserCustom userCustom, HttpServletRequest request, String veryCode, HttpServletResponse response)
			 throws Exception{
		String checkcode = (String) request.getSession().getAttribute("checkcode_session");
		if(!checkcode.equals(veryCode)){
			request.setAttribute("ckcode_msg", "验证码错误");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else{
		UserCustom uc =  userService.userLogin(userCustom);
		if(uc!=null){
			String role = userService.userRole(uc);
			System.out.println("用户身份为"+role);
			if("管理员".equals(role)){
				User user = new User();
				BeanUtils.copyProperties(uc, user);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/jsp/success_g.jsp").forward(request, response);
			}else{
				User user = new User();
				BeanUtils.copyProperties(uc, user);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/jsp/success_p.jsp").forward(request, response);
			}
		}else {
			 request.setAttribute("login_state", "用户名或密码错误");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		        }
		}	
	 }
	/**
	 * 
	 *方法的作用：用户注册
	 *方法名：userRegister
	 *创建时间：2018年5月17日-下午4:19:36
	 *@author LZL
	 *@return void
	 * @param model
	 * @param userCustom
	 * @param request
	 * @param veryCode
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userRegister")
	 public void userRegister(Model model,UserCustom userCustom,HttpServletRequest request,String veryCode,HttpServletResponse response)throws Exception{
		String checkcode = (String) request.getSession().getAttribute("checkcode_session");
		if(!checkcode.equals(veryCode)){
			request.setAttribute("ckcode_msg", "验证码错误");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
			userService.userRegister(userCustom);
			
		    response.sendRedirect("reg_result.jsp");
		      }
	 }
	/**
	 * 
	 *方法的作用：ajax注册校验
	 *方法名：ajaxRegister
	 *创建时间：2018年5月17日-下午4:18:34
	 *@author LZL
	 *@return String
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/ajaxRegister")
    public String ajaxRegister(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	String name = request.getParameter("username");
    	if(name!=null && name!=""){
    	UserCustom uc = userService.ajaxRegister(name);
    	if(uc!=null){
    		/*PrintWriter out = response.getWriter();
    		JSONArray array = JSONArray.fromObject("用户名已存在");
    		out.write(array.toString());
    		out.close();*/
    	//	JSONObject json = new JSONObject();
    	//	json.put("用户名不存在", JSONObject.toJSON(json));
    		//return json.toJSONString();
    		return "用户名已存在";
    	}
    	return "可以使用该用户名";
    }
    	return null;
	}
	/**
	 * 
	 *方法的作用：用户推出
	 *方法名：userExit
	 *创建时间：2018年5月17日-下午4:18:39
	 *@author LZL
	 *@return void
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//用户注销
	@RequestMapping("/userExit")
	public void userExit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//request.getSession().getAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("main.jsp");
	}
	/**
	 * 
	 *方法的作用：修改用户信息跟据用户ID
	 *方法名：updateUser
	 *创建时间：2018年5月17日-下午12:30:25
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@RequestMapping("/updateUserById")
	public void updateUserById(UserCustom userCustom,HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("进入到这个方法李了");
		userService.updateUserById(userCustom);
		System.out.println("2");
		response.sendRedirect("showAllUser.action");
	}
	/**
	 * 
	 *方法的作用：删除用户根据用户id
	 *方法名：deleteUser
	 *创建时间：2018年5月17日-下午12:31:22
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@RequestMapping("/deleteUserById")
	public void deleteUserById(int id,HttpServletRequest request,HttpServletResponse response)throws Exception{
		userService.deleteUserById(id);
		response.sendRedirect("showAllUser.action");
	}
	/**
	 * 
	 *方法的作用：显示所有用户信息
	 *方法名：showAllUser
	 *创建时间：2018年5月17日-下午1:13:07
	 *@author LZL
	 *@return void
	 * @throws Exception
	 */
	@RequestMapping("/showAllUser")
	public void showAllUser(HttpServletRequest request,HttpServletResponse response)throws Exception{
		List<UserCustom> userList  = userService.showAllUser();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("jsp/alluser.jsp").forward(request, response);
	}
	/**
	 * 
	 *方法的作用：根据用户id查看用户信息
	 *方法名：showUserById
	 *创建时间：2018年5月17日-下午1:36:13
	 *@author LZL
	 *@return void
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/showUserById")
	public void showUserById(int id, HttpServletRequest request,HttpServletResponse response) throws Exception {
		UserCustom user = userService.showUserById(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/edituser.jsp").forward(request,response);
	}
}
