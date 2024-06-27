package com.lzl.demo1.service.impl;

import com.lzl.demo1.annotation.Master;
import com.lzl.demo1.entity.User;
import com.lzl.demo1.service.UserService;
import com.lzl.demo1.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    @Master
    @Override
    public Integer batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }
}
