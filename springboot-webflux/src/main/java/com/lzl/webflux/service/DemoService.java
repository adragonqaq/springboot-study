package com.lzl.webflux.service;

import com.lzl.webflux.bean.User;

import java.util.List;

public interface DemoService {


    String getOneResult(String methodName);


    List<String> getMultiResult(String methodName);

    User addUser(User user);

    List<User> findAllUser();

    User findUserById(Long id);

}
