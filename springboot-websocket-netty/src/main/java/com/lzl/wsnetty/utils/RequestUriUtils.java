package com.lzl.wsnetty.utils;



import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * URI 工具类，用于解析 HTTP 请求 URI 中的参数和路径。
 */
public class RequestUriUtils {

    /**
     * 从给定的 URI 字符串中提取查询参数。
     *
     * @param uri 完整的 URI 字符串，例如 "/websocket?param1=value1&param2=value2"
     * @return 包含所有查询参数的 Map，键为参数名，值为参数值。
     */
    public static Map<String, String> getParams(String uri) {
        Map<String, String> params = new HashMap<>();
        try {
            // 分割路径和查询参数
            String[] uriParts = uri.split("\\?", 2);
            if (uriParts.length < 2 || uriParts[1].isEmpty()) {
                return params; // 没有查询参数
            }

            String queryString = uriParts[1];
            // 分割各个参数对
            String[] pairs = queryString.split("&");

            for (String pair : pairs) {
                // 分割键和值
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    // URL 解码键和值，处理特殊字符
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = URLDecoder.decode(keyValue[1], "UTF-8");
                    params.put(key, value);
                } else if (keyValue.length == 1) {
                    // 处理没有值的参数，例如 "?param1"
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    params.put(key, "");
                }
            }
        } catch (Exception e) {
            // 解析失败时返回空 Map 或根据需要处理异常
            // 这里选择忽略异常并返回已解析的部分（如果有的话）
        }
        return params;
    }

    /**
     * 从可能包含查询参数的路径字符串中提取基础路径。
     *
     * @param path 可能包含查询参数的路径，例如 "/websocket?param1=value1"
     * @return 基础路径，例如 "/websocket"
     */
    public static String getBasePath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        // 使用 "?" 分割路径和查询参数，并返回第一部分（即基础路径）
        int questionMarkIndex = path.indexOf('?');
        if (questionMarkIndex != -1) {
            return path.substring(0, questionMarkIndex);
        }
        return path; // 如果没有查询参数，直接返回原路径
    }
}

    
    
    