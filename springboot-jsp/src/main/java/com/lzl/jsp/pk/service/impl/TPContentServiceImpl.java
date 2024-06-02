package com.lzl.jsp.pk.service.impl;


import com.lzl.jsp.pk.mapper.TPContentMapper;
import com.lzl.jsp.pk.pojo.TPContentCustom;
import com.lzl.jsp.pk.service.TPContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TPContentServiceImpl implements TPContentService {
 
	@Autowired
	private TPContentMapper tpContentMapper;
	 
	@Override
	public void savePiKeContent(TPContentCustom tpContentCustom)
			throws Exception {
		tpContentMapper.savePiKeContent(tpContentCustom);
	}

	@Override
	public TPContentCustom showPiKeByClazzName(String clazz_name)
			throws Exception {
		return tpContentMapper.showPiKeByClazzName(clazz_name);
	}

	@Override
	public void updatePiKeByClazzName(TPContentCustom tpContentCustom)
			throws Exception {
		tpContentMapper.updatePiKeByClazzName(tpContentCustom);
	}

}
