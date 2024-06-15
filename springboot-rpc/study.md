https://blog.csdn.net/hc1285653662/article/details/127001439
HttpClient和OkHttp


区别
OkHttp创建客户端的方式简单一点
OkHttp使用.post/.delete/.put/.get方法表示请求类型；HttpClient创建HttpGet、HttpPost等这些方法来创建请求类型
如果HttpClient需要发送异步请求，需要额外的引入异步请求依赖
单例模式下，HttpClient的响应速度要更快一些；非单例模式下，OkHttp的性能更好
