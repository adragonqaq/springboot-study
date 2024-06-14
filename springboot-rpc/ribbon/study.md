相关链接：

> https://blog.csdn.net/qq_33204709/article/details/131566762



`Ribbon` 是 Netflix 推出的开源框架，其主要功能是实现客户端的负载均衡算法

```
<!-- Ribbon -->
<dependency>
    <groupId>com.netflix.ribbon</groupId>
    <artifactId>ribbon</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.netflix.ribbon</groupId>
    <artifactId>ribbon-httpclient</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.netflix.ribbon</groupId>
    <artifactId>ribbon-loadbalancer</artifactId>
    <version>2.3.0</version>
</dependency>
```



`Spring Cloud Ribbon` 是基于 Netflix 公司的 `Ribbon` 进一步封装和扩展。

```
<!-- Spring Cloud Ribbon -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
```



`Spring Cloud Loadbalancer` 是 Spring Cloud 官方自己实现的客户端负载均衡器，是 Netflix Ribbon 的替换方案

```
<!-- Spring Cloud Loadbalancer -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-loadbalancer</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
```

原生相关API使用
ribbon 的 github地址
https://github.com/Netflix/ribbon



如何使用原生的Ribbon
https://www.cnblogs.com/ZhangZiSheng001/p/15484505.html
