Spring Boot使用HikariCP作为默认的数据库连接池。如果你需要配置数据库连接池，可以在application.properties或application.yml文件中设置相关属性。

以下是一个application.properties的配置示例：

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hikari 连接池配置

spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.pool-name=YourPoolName
```

如果你使用application.yml

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      pool-name: YourPoolName
```

MySQL，你需要在pom.xml中添加：

```
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>Your-MySQL-Connector-Version</version>
</dependency>
```

Spring Boot会自动配置HikariCP，
所以通常不需要额外的配置。
如果需要自定义HikariCP，可以在application.properties或application.yml中添加更多的spring.datasource.hikari前缀的属性。