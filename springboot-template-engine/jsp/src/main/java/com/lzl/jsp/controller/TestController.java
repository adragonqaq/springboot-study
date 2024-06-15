package com.lzl.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/getPerson")
    public String returnPerson(Model model) {
        model.addAttribute("person", "你好，jsp！"); // 为页面设置属性
        return "person"; // 返回person.jsp页面的名称person
    }
}
