### Servlet与jsp

#### 什么是Servlet？
    
    Servlet是一个独立的Web开发的标准，它是一种基于Java技术的Web组件。在浏览器或是http协议的客户端与服务器的交互中，Servlet承担着处理
    请求和生成响应信息的责任。

#### Servlet容器
    
    提到Servlet就必须了解Servlet容器，Tomcat就是一个Servlet容器。它负责接收客户端的请求，将请求信息发送至对应的Servlet进行处理，并将
    处理之后的信息发送至客户端。
    
#### Servlet与jsp的区别与联系
    
    区别：
        1.Jsp在本质上是Servlet，Jsp在通过编译会生成一个Servlet。
        2.Jsp主要用于展示层，而Servlet主要用于逻辑处理。
        3.Jsp有内置对象，而Servlet需要通过HttpServletRequest，HttpServletResponse或HttpServlet获取。
    
    联系：
        1.Jsp是Servlet技术上的扩展，本质上是Servlet的简易方式。
        2.Jsp是java代码、html的一种组合。

    
    
#### 服务器响应过程
    
    1.浏览器向web服务器发送http请求
    2.Servlet容器接收到对应的请求，创建ServletRequest对象，并将请求信息封装值request对象中。
    3.Servlet容器创建ServletResponse对象。
    4.Servlet容器根据请求查找对应的Servlet，若不存在则初始化一个Servlet。
    5.调用Servlet的service方法，处理对应的请求。
    6.Servlet容器将响应信息封装至ServletResponse对象中。
    7.将响应信息返回值浏览器。
    
#### Servlet的生命周期
Servlet的生命周期指的是Servlet从被web服务器加载直到销毁的整个生命过程。
    
    1.init：
        当web服务器加载指定的Servlet时，在构造对应的Servlet对象之后，会调用init()方法完成Servlet的初始化工作，在一个Servlet的生命周
        期中，只会执行一次init()方法。
        
        注：web服务器加载Servlet的时机由load-on-startup配置参数决定
            1.1 当load-on-startup>=0时，会在web服务器启动时加载对应的Servlet，且值越小优先级越高。
            1.2 未指定load-on-startup或值为负数，则在第一次对Servlet发起请求时加载对应的Servlet。
            1.3 当load-on-startup值相同时，web服务器会自己选择加载顺序，测试时未发现对应规律。
        
    2.service：
        service(ServletRequest req, ServletResponse res)方法是一个Servlet的核心（HttpServlet中已经存在对应的service()方法，在
        请求过程中会根据请求来选择对应doXXX()方法），用来处理对应的请求。
    
    3.destroy：
        当web服务器卸载对应的Servlet时，会调用destroy()方法。当Servlet退出时，需要释放对应的资源。在Servlet的service()方法在执行时
        可能会产生一些其它线程，因此在执行destroy()方法时，需要确定这些资源是否已经释放。在一个Servlet的生命周期中，只会执行一次destroy()
        方法。
        

#### jsp内置对象
	
	1.request
		用于存储由客户端的请求信息，通常指由http协议传输至服务端的数据。
	2.response
		javax.servlet.http.HttpServletResponse的对象
		代表服务端的响应信息，将jsp容器处理的结果返回值客户端。
		作用域为page
	3.session
		是服务器产生的与用户请求相关的对象，用于存储用户信息，以及跟踪用户的状态。服务器会为每个用户都生成的一个session。
	4.page
		javax.servlet.jsp.HttpJspPage的对象
		表示jsp转换后的servlet本身，类似于this关键字
		作用域为page
	5.pageContext
		javax.servlet.jsp.PageContext的对象
		本JSP的页面上下文，可以通过pageContext获取到当前页面的所有信息。
	6.config
		主要作用是取得服务器的配置信息。
	7.application
		application 对象可将信息保存在服务器中，直到服务器关闭，否则application对象中保存的信息会在整个应用中都有效。与session对象相
		比，application对象生命周期更长，类似于系统的“全局变量”。
	8.out
		用于在页面中输出信息，并且管理服务器的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数
		据，为其他的输出让出缓冲空间。待数据输出完毕后，要及时关闭输出流。
	9.exception 
		exception 对象的作用是显示异常信息，只有在包含 isErrorPage="true" 的页面中才可以被使用，在一般的JSP页面中使用该对象将无法编
		译JSP文件。excepation对象和Java的所有对象一样，都具有系统提供的继承结构。exception 对象几乎定义了所有异常情况。在Java程序中，可以使用try/catch关键字来处理异常情况； 如果在JSP页面中出现没有捕获到的异常，就会生成 exception 对象，并把 exception 对象传送到在page指令中设定的错误页面中，然后在错误页面中处理相应的 exception 对象。

#### 请求转发的三种方式
    
    1.转发（forward）
        将请求转发到其他地址，在这个过程中使用同一个请求，转发地址栏的地址保持不变。
        
    2.重定向（redirect）
        过程中包含两次请求，指的是将原有的请求地址重新定位到某个新地址，原有的请求将失效，客户端所看到的是新的请求的结果。
    
        
#### Servlet配置
    
1.创建一个Servlet类可以有多种方法，实现Servlet接口，继承GenericServlet或HttpServlet，GenericServlet实现了Servlet接口中的方法，而
HttpServlet又继承至GenericServlet，且HttpServlet实现了自己的service方法，并根据对应的请求方式选择需要执行的方法。因此，在通常情况下
我们只需要继承HttpServlet并重写doGet或是doPost方法即可。

2.在web.xml文件中配置Servlet，对一个Servlet进行配置时，需要配置servlet和servlet-mapping。
        
    <!--Servlet配置-->
    <servlet>
        <!--Servlet的名称，自定义-->
        <servlet-name>httpServletTest</servlet-name>
        <!--Servlet的类路径-->
        <servlet-class>com.java.summary.web.servlet.HttpServletTest</servlet-class>
    </servlet>
    <!--Servlet映射信息配置-->
    <servlet-mapping>
        <!--Servlet的名称，需要与指定的Servlet名称相同-->
        <servlet-name>httpServletTest</servlet-name>
        <!--Servlet的访问路径-->
        <url-pattern>/httpServlet</url-pattern>
    </servlet-mapping>
    
3.配置文件中的的映射路径
    
    .1 精确匹配：/httpServlet  访问路径为：http://localhost:8080/XXX/servlet
    
    .2 模糊匹配：/*  访问路径为：http://localhost:8080/XXX/任意值
                /zzz/*  访问路径为：http://localhost:8080/XXX/zzz/任意值
                /*.do 指定对应的后缀名   访问路径为：http://localhost:8080/XXX/任意值.do
                
     注：映射路径的中“/”不可省略。
     
    
    