package com.lzl.jsp.pk.mapper;

import com.lzl.jsp.pk.pojo.TeachPlanCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeachPlanMapper {
	//添加教学计划
	 public void addTeachPlan(TeachPlanCustom teachPlanCustom)throws Exception;
	 //查看所有教学计划
	 public List<TeachPlanCustom> findTeachPlanByName(String str)throws Exception;
	 //加入教室
	 public void updateTeachPlanByid(TeachPlanCustom teachPlanCustom)throws Exception;
	 //查询班级一周上课总时间
	 
}
