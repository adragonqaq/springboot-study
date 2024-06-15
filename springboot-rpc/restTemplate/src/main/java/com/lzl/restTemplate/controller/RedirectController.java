package com.lzl.restTemplate.controller;


import com.lzl.restTemplate.Bean.RequestBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {


    /**
     * 重定向
     * @param request
     * @return
     */
    @RequestMapping(value = "testPostByLocation", method = RequestMethod.POST)
    public String testPostByLocation(@RequestBody RequestBean request){
        return "redirect:index.html";
    }
}
