package com.lzl.demo1.service;

import com.lzl.demo1.entity.Member;

import java.util.List;

public interface MemberService {

    int insert(Member member);


    int save(Member member);

    List<Member> selectAll();


    String getToken(String appId);
}
