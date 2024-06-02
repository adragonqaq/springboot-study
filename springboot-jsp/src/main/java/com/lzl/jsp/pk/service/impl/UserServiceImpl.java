package com.lzl.jsp.pk.service.impl;


import java.util.List;

import com.lzl.jsp.pk.mapper.UserMapper;
import com.lzl.jsp.pk.pojo.UserCustom;
import com.lzl.jsp.pk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserCustom userLogin(UserCustom userCustom) throws Exception {
		return userMapper.userLogin(userCustom);
	}

	@Override
	public void userRegister(UserCustom userCustom) throws Exception {
		userMapper.userRegister(userCustom);
	}

	@Override
	public UserCustom ajaxRegister(String name) throws Exception {
		return userMapper.ajaxRegister(name);
	}

	@Override
	public String userRole(UserCustom userCustom) throws Exception {
		return userMapper.userRole(userCustom);
	}

	@Override
	public void deleteUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		userMapper.deleteUserById(id);
	}

	@Override
	public void updateUserById(UserCustom userCustom) throws Exception {
		// TODO Auto-generated method stub
		userMapper.updateUserById(userCustom);
	}

	@Override
	public List<UserCustom> showAllUser() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.showAllUser();
	}

	@Override
	public UserCustom showUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.showUserById(id);
	}

}
