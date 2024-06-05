来源：https://www.cnblogs.com/haixiang/p/11568659.html


SpringCloud Sleuth 简介#
Spring Cloud Sleuth为Spring Cloud实现了分布式跟踪解决方案。

Spring Cloud Sleuth借鉴了Dapper的术语。

Span：基本的工作单元。Span包括一个64位的唯一ID，一个64位trace码，描述信息，时间戳事件，key-value 注解(tags)，span处理者的ID（通常为IP）。

Trace：一组Span形成的树形结构。

Annotation：用于及时记录存在的事件。常用的Annotation如下：

cs：客户端发送(client send) 客户端发起一个请求，表示span开始
sr：服务器接收(server received) 服务器接收到客户端的请求并开始处理，sr - cs 的时间为网络延迟
ss：服务器发送(server send) 服务器处理完请求准备返回数据给客户端。ss - sr 的时间表示服务器端处理请求花费的时间
cr：客户端接收(client received) 客户端接收到处理结果，表示span结束。 cr - cs 的时间表示客户端接收服务端数据的时间



启动 zipkin，注意参数

这种默认http传输
```java
java -jar zipkin-server-2.16.2-exec.jar

```




这种方式使用 rabbitmq 传输span, 前提是启动 rabbitmq

```java
java -jar zipkin-server-2.16.2-exec.jar --RABBIT_ADDRESSES=localhost:5672 --RABBIT_USER=guest --RABBIT_PASSWORD=guest --RABBIT_VIRTUAL_HOST=/

```

在测试的时候发现 mq 和以上方式时延相差无几，但是随着线程数的增加也就是并发量的增加，mq 传输时延将会大大低于 http。




