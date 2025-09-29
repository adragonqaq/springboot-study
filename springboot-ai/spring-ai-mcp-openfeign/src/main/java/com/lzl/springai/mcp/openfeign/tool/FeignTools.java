package com.lzl.springai.mcp.openfeign.tool;


import com.lzl.springai.mcp.openfeign.feign.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/29 10:16
 **/
@Component
public class FeignTools {

    @Autowired
    private HelloClient helloClient;

    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        return helloClient.getCurrentDateTime();
    }

    @Tool(description = "Set a user alarm for the given time, provided in ISO-8601 format")
    String setAlarm(@ToolParam(description = "Time in ISO-8601 format", required = true) String time) {
        return helloClient.setAlarm(time);
    }
}
    
    
    