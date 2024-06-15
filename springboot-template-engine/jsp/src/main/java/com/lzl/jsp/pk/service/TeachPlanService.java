package com.lzl.jsp.pk.service;

import com.lzl.jsp.pk.pojo.TeachPlanCustom;

import java.util.List;


public interface TeachPlanService {
	 public void addTeachPlan(TeachPlanCustom teachPlanCustom)throws Exception;
	 //查看所有教学计划
	 public List<TeachPlanCustom> findTeachPlanByName(String str)throws Exception;
	 //加入教室
	 public void updateTeachPlanByid(TeachPlanCustom teachPlanCustom)throws Exception;
}
