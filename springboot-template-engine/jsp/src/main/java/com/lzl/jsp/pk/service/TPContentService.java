package com.lzl.jsp.pk.service;


import com.lzl.jsp.pk.pojo.TPContentCustom;

public interface TPContentService {
		//保存排课信息
		public void savePiKeContent(TPContentCustom tpContentCustom)throws Exception;
		//根据班级名称查找班级排课信息
		public TPContentCustom showPiKeByClazzName(String clazz_name)throws Exception; 
		//根据班级名称修改排课信息
		public void updatePiKeByClazzName(TPContentCustom tpContentCustom)throws Exception;
}
