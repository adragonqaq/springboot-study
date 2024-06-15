package com.lzl.jsp.pk.service;

import com.lzl.jsp.pk.pojo.ClazzCustom;

import java.util.List;


public interface ClazzService {
	public void addClass(ClazzCustom clazzCustom)throws Exception;
	 //查看所有班级
	 public List<ClazzCustom> selectClazz()throws Exception;
	//通过班级名称查询班级人数
	 public int findPresonByName(String str)throws Exception;
}
