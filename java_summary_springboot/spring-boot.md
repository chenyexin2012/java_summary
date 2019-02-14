###　spring-boot常用依赖模块

- spring-boot-starter-web：web开发依赖模块

- spring-boot-starter-logging：日志依赖模块


###  spring-boot的核心注解

- @SpringBootApplication：它是一个组合注解，该注解组合了：@Configuration、@EnableAutoConfiguration、@ComponentScan； 
若不是用 @SpringBootApplication 注解也可以使用这三个注解代替。

- @SpringBootConfiguration：

- @EnableAutoConfiguration：让 Spring Boot 根据类路径中的 jar 包依赖为当前项目进行自动配置，例如，添加了 spring-boot-starter-web 
依赖，会自动添加 Tomcat 和 Spring MVC 的依赖，那么 Spring Boot 会对 Tomcat 和 Spring MVC 进行自动配置。



### spring-boot参数配置

[https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/htmlsingle/#common-application-properties]

 
### spring-boot入门博客

快速入门 [https://www.cnblogs.com/wmyskxz/p/9010832.html]
