package com.lzl.demo2.mapper.db2;

import com.lzl.demo2.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface User2Mapper {


    Integer batchInsert(@Param("list") List<User> list);
}
