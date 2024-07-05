https://blog.csdn.net/hc1285653662/article/details/126982218

https://www.jianshu.com/p/58949f8334f5

https://www.cnblogs.com/hellosyl/p/18054333

https://blog.csdn.net/lrb0677/article/details/125510456


1、什么是RestTemplate？
RestTemplate是Spring提供的进行远程调用客户端
RestTemplate提供了很多远程调用的方法，能够大大提高客户端的编写效率。
调用RestTemplate的默认构造函数，RestTemplate对象在底层通过使用java.net包下的实现创建HTTP 请求
可以通过使用ClientHttpRequestFactory指定不同的HTTP请求方式
ClientHttpRequestFactory接口主要提供了三种实现方式

一种是SimpleClientHttpRequestFactory，使用J2SE提供的方式（既java.net包提供的方式）创建底层的Http请求连接
一种方式是使用HttpComponentsClientHttpRequestFactory方式，底层使用HttpClient访问远程的Http服务，使用HttpClient可以配置连接池和证书等信息
第三种方式是使用OkHttp3ClientHttpRequestFactory方式，底层使用OkHttp访问远程的Http服务，使用HttpClient可以配置连接池和证书等信息
2、RestTemplate的优缺点
优点：连接池、超时时间设置、支持异步、请求和响应的编解码
缺点：依赖别的spring版块、参数传递不灵活



RestTemplate默认是使用SimpleClientHttpRequestFactory，内部是调用jdk的HttpConnection，默认超时为-1



springboot 自动断开会话

，servlet 容器会创建一个会话，并将会话的唯一标在 Spring Boot 中，会话管理是由 servlet 容器负责的。当客户端与服务器建立连接后，识符(session lD)通过 Cookie 或 URL 传递给客户端。默认情下，会话在客户端关闭或超过一定时间(由服务器配置)后会自动断开。
如果你想在 Spring Boot 中自定义会话的超时时间，可以在应用配置文件(如 application.properties 或 application.yml)中添加以下配置


springboot配置http连接超时时间主动断开避免产生大量close_wait
https://blog.csdn.net/qq_36256590/article/details/130773174