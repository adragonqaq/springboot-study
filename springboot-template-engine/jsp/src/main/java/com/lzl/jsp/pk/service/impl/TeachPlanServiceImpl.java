package com.lzl.jsp.pk.service.impl;


import java.util.List;

import com.lzl.jsp.pk.mapper.TeachPlanMapper;
import com.lzl.jsp.pk.pojo.TeachPlanCustom;
import com.lzl.jsp.pk.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeachPlanServiceImpl implements TeachPlanService {

	 @Autowired
	 private TeachPlanMapper teachPlanMapper;
	 
	@Override
	public void addTeachPlan(TeachPlanCustom teachPlanCustom) throws Exception {
		// TODO Auto-generated method stub
		teachPlanMapper.addTeachPlan(teachPlanCustom);
	}

	@Override
	public List<TeachPlanCustom> findTeachPlanByName(String str)
			throws Exception {
		return teachPlanMapper.findTeachPlanByName(str);
	}

	@Override
	public void updateTeachPlanByid(TeachPlanCustom teachPlanCustom)
			throws Exception {
		teachPlanMapper.updateTeachPlanByid(teachPlanCustom);
	}

}
