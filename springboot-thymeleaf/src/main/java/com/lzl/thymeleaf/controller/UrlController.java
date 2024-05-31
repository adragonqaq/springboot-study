package com.lzl.thymeleaf.controller;


import com.lzl.thymeleaf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UrlController {
    @GetMapping("index")//页面的url地址
    public String getindex(Model model)//对应函数
    {
        model.addAttribute("name","bigsai");
        return "index";//与templates中index.html对应
    }


    @GetMapping("index2")//页面的url地址
    public String getindex2(Model model)//对应函数
    {
        User user1=new User("bigsai",22,"一个幽默且热爱java的社会青年");
        List<String> userList=new ArrayList<>();
        userList.add("zhang san 66");
        userList.add("li si 66");
        userList.add("wang wu 66");
        Map<String ,String> map=new HashMap<>();
        map.put("place","博学谷");
        map.put("feeling","very well");
        //数据添加到model中
        model.addAttribute("name","bigsai");//普通字符串
        model.addAttribute("user",user1);//储存javabean
        model.addAttribute("userlist",userList);//储存List
        model.addAttribute("map",map);//储存Map
        return "index2";//与templates中index.html对应
    }


}