package com.lzl.springai.mcp.openfeign.config;


import com.lzl.springai.mcp.openfeign.tool.FeignTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/29 10:24
 **/
@Component
public class McpServerConfig {

    @Autowired
    private FeignTools feignTools;

    @Bean
    public ToolCallbackProvider timeTools() {
        return MethodToolCallbackProvider.builder().toolObjects(feignTools).build();
    }
}
    
    
    