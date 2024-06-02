package com.lzl.jsp.pk.mapper;
import com.lzl.jsp.pk.pojo.ClazzCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
	//添加班级
	 public void addClass(ClazzCustom clazzCustom)throws Exception;
	 //查看所有班级
	 public List<ClazzCustom> selectClazz()throws Exception;
	 //通过班级名称查询班级人数
	 public int findPresonByName(String str)throws Exception;
}
