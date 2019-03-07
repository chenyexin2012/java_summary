## java基础

synchronized修饰静态方法和修饰类方法的不同

动态代理

	jdk: InvocationHandler  --> invoke(proxy, method, args) --> Proxy.newInstance(classloader, interfaces, invocationHandler)
	
	cglib: MethodInterceptor --> intercept(obj, method, args, proxy) --> Enhancer  classloader, superclass, MethodInterceptor


## 不同服务之间调用的常用方式

	1.RPC：远程过程调用，每个服务都运行在自己的JVM中，用户分布式系统中各个服务之间的调用。
		
		包含技术：序列化、反序列化、数据传输协议。
		
		目前Java使用比较多的RPC方案主要有RMI（JDK自带）、Hessian、Dubbo以及Thrift等。
		
	2.RESTFul：
	
		GET、PUT、DELETE、POST
		
		
## 常用的序列化框架

	序列化：将java对象转化成二进制文件
	反序列化：
	
	
	kryo、hessian、java、protostuff


## 设计模式
    
常见设计模式的优点以及缺点


## 数据库

mysql

	存储引擎 常见 MyISAM和InnoDB
	
	
		MyISAM只支持表锁，MyISAM不支持事务和行级锁
	
		InnoDB支持表锁与行锁
		
		MyISAM更适合读密集的表，而InnoDB更适合写密集的的表

	行级锁，表级锁，页级锁

		表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。
		行级锁：开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。
		页面锁：开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般


	悲观锁，乐观锁

	数据库索引

	    唯一索引、主键索引、联合索引

        主键索引也是唯一索引，但唯一索引不一定是主键索引
        一张表可以有多个唯一索引，但只能有一个主键索引
        主键索引不能为null，而唯一索引可以为null且可以有多个

        联合索引：最左前缀

        innoDB中索引的数据结构：B+Tree

        索引什么情况下会失效？
	
事务

	事务是一系列对数据库操作的程序集合。

	四大特性
		原子性： 事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用；
		一致性： 执行事务前后，数据库从一个一致性状态转换到另一个一致性状态。
		隔离性： 并发访问数据库时，一个用户的事物不被其他事务所干扰，各并发事务之间数据库是独立的；
		持久性： 一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库 发生故障也不应该对其有任何影响。

	隔离级别
	
	
存储过程

    

## spring 

spring mvc 与 struts2 的区别

spring mvc的重定向和转发

spring mvc的工作原理

	
	核心类：DispatcherServlet
	
	
	DispatcherServlet  --> HandlerMappering --> HandlerAdapter --> ModelAndView --> DispatcherServlet
	
	
	（1）客户端（浏览器）发送请求，直接请求到 DispatcherServlet。

	（2）DispatcherServlet 根据请求信息调用 HandlerMapping，解析请求对应的 Handler。

	（3）解析到对应的 Handler（也就是我们平常说的 Controller 控制器）后，开始由 HandlerAdapter 适配器处理。

	（4）HandlerAdapter 会根据 Handler 来调用真正的处理器开处理请求，并处理相应的业务逻辑。

	（5）处理器处理完业务后，会返回一个 ModelAndView 对象，Model 是返回的数据对象，View 是个逻辑上的 View。

	（6）ViewResolver 会根据逻辑 View 查找实际的 View。

	（7）DispaterServlet 把返回的 Model 传给 View（视图渲染）。

	（8）把 View 返回给请求者（浏览器）

## mybatis

“#{}” 和 “${}” 的区别

Mybatis和JDBC区别

	Mybatis是一个支持sql查询、存储过程以及高级映射的持久化框架。
	
	Mybatis消除了JDBC的参数封装和结果集封装。
	
	Mybatis可以使用xml或注解来进行配置，以及将pojo类映射为数据库记录。

	Mybatis可以使用各种数据库连接池。
	
	从代码维护角度，Mybatis的代码可读性较强，jdbc中sql语句分散在各个java代码中，不易于维护，而mybatis将sql语句写在配置文件中，易于管理。
	
	Mybatis可以动态的生成sql。


## 网络协议

TCP的三次握手和四次挥手的过程。

四次挥手中客户端在TIME-WAIT状态为什么要等待2MSL时间才能进入CLOSED状态？
    

## 同步与异步 阻塞与非阻塞

同步：调用方主动等待被调用方返回结果。

异步：调用方不需要等待被调用方返回结果，而是通过其它手段，如状态通知，回调函数等。

阻塞：指结果返回之前，当前线程被挂起。

非阻塞：指返回结果之前，当前线程可以做其它事，不会被挂起。


## 消息队列

应用场景：解耦、异步、削峰

MQ保证消息传递的可靠性：事务 + 签收方式

如何防止消息被重复消费