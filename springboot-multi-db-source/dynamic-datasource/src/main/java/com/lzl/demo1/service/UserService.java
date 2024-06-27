package com.lzl.demo1.service;

import com.lzl.demo1.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<User> selectAll();

    Integer batchInsert(List<User> list);
}
