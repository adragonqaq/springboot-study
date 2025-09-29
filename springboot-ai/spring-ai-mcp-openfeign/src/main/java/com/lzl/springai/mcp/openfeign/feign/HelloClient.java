package com.lzl.springai.mcp.openfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/29 10:15
 **/
@FeignClient(name = "provider", url = "http://localhost:8889")
@Description("Get the current date and time in the user's timezone")
public interface HelloClient {

    @GetMapping("/time/current")
    String getCurrentDateTime();

    @PostMapping("/time/setAlarm")
    String setAlarm(@RequestParam String time);
}

    
    
    