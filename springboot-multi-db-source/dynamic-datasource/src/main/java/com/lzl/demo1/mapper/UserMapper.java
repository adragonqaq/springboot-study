package com.lzl.demo1.mapper;


import com.lzl.demo1.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    Integer batchInsert(@Param("list") List<User> list);
}
