package com.lzl.tkmybatis.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 增强mapper基类，支持批量操作，其他mapper继承此mapper
 */
public interface BaseTkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
