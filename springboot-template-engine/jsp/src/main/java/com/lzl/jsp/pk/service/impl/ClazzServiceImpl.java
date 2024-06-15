package com.lzl.jsp.pk.service.impl;


import java.util.List;

import com.lzl.jsp.pk.mapper.ClazzMapper;
import com.lzl.jsp.pk.pojo.ClazzCustom;
import com.lzl.jsp.pk.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzMapper clazzMapper;
	
	@Override
	public void addClass(ClazzCustom clazzCustom) throws Exception {
		// TODO Auto-generated method stub
		clazzMapper.addClass(clazzCustom);
	}

	@Override
	public List<ClazzCustom> selectClazz() throws Exception {
		// TODO Auto-generated method stub
		return clazzMapper.selectClazz();
	}

	@Override
	public int findPresonByName(String str) throws Exception {
		// TODO Auto-generated method stub
		return clazzMapper.findPresonByName(str);
	}

}
