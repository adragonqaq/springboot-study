package com.lzl.jsp.pk.service;

import com.lzl.jsp.pk.pojo.UserCustom;

import java.util.List;


public interface UserService {
	    //用户登陆
		public UserCustom userLogin(UserCustom userCustom) throws Exception;
		//用户注册
		public void userRegister(UserCustom userCustom) throws Exception;
		//ajax验证
		public UserCustom ajaxRegister(String name)throws Exception;
		//查询用户权限
		public String userRole(UserCustom userCustom)throws Exception;
		//删除用户信息根据id
		public void deleteUserById(int id)throws Exception;
		//修改用户信息根据id
		public void updateUserById(UserCustom userCustom)throws Exception;
		//显示所有用户信息
		public List<UserCustom> showAllUser()throws Exception;
		//显示所有用户信息根据用户id
		public UserCustom showUserById(int id)throws Exception;
}
