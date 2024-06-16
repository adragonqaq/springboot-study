package com.lzl.tkmybatis.service.impl;

import com.lzl.tkmybatis.entity.MymallRole;
import com.lzl.tkmybatis.mapper.MymallMapper;
import com.lzl.tkmybatis.service.MymallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MymallServiceImpl implements MymallService {

    @Autowired
    private MymallMapper mymallMapper;


    @Override
    public List<MymallRole> getRoles() {
        Example example = new Example(MymallRole.class);
        example.selectProperties("name","enabled");
        return mymallMapper.selectByExample(example);
    }
}
