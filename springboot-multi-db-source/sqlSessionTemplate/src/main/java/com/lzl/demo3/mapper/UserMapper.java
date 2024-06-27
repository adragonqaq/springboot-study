package com.lzl.demo3.mapper;


import com.lzl.demo3.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    Integer batchInsert(@Param("list") List<User> list);
}
