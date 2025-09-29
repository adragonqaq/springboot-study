package com.lzl.springai.mcp.openfeign.controller;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/29 10:15
 **/
@RestController
@RequestMapping("/time")
public class TimeController {

    @RequestMapping("/current")
    String getCurrentDateTime(HttpServletRequest httpRequestHandler) {
        System.out.println("Get the current date!");
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @PostMapping("/setAlarm")
    String setAlarm(@RequestParam("time") String time) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Alarm set for " + alarmTime);
        return"Alarm set for " + alarmTime;
    }
}
    
    
    