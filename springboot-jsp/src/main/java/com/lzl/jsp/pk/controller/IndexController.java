package com.lzl.jsp.pk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/main")
    public String returnPerson(Model model) {
        return "main";
    }
}
