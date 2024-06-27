package com.lzl.demo1.service.impl;


import com.lzl.demo1.annotation.Master;
import com.lzl.demo1.entity.Member;
import com.lzl.demo1.mapper.MemberMapper;
import com.lzl.demo1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {


    @Resource
    private MemberMapper memberMapper;

    @Transactional
    @Override
    public int insert(Member member) {
//        return memberMapper.insert(member);
        return 1;
    }

    @Master
    @Override
    public int save(Member member) {
//        return memberMapper.insert(member);
        return 1;
    }

    @Override
    public List<Member> selectAll() {
//        return memberMapper.selectByExample(new MemberExample());
        return null;
    }

    @Master
    @Override
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}
