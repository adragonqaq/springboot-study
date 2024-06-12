package com.lzl.openfeign.api;


import com.lzl.openfeign.bean.User;
import feign.Param;
import feign.Request;
import feign.RequestLine;

public interface TestApi {

    /*
    请注意，与Spring MVC中的注解不同，

    Feign的@RequestLine注解没有提供像produces、consumes、params和headers等属性来进行更详细的请求匹配和条件设置。
    如果您需要进一步的请求配置，通常需要结合其他的注解或配置来完成，比如使用@Headers注解来设置请求头。

     */

    @RequestLine("GET /user/{userId}")
    User queryUser(@Param("userId") Integer userId);


    @RequestLine("GET /user/{userId}")
    User queryUser2(@Param("userId") Integer userId, Request.Options options);
}
