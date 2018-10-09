
#### Filter
Filter也称之为过滤器，是java Web的三大组件之一。对于一个Web应用程序来说，它是处理服务器端的一个组件，可以截取客户端请求信息和服务端的响应
信息。Web开发人员通过Filter技术，对web服务器管理的所有web资源，如Jsp, Servlet, 静态图片文件或静态html文件等进行拦截，从而实现一些特殊
的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。

#### Filter生命周期

    1.init
        与Servlet相同，Filter的创建和销毁都是有Web服务器来负责。当Web应用程序启动时，Web服务器将会创建一个Filter的实例，并调用其init()
    方法，完成初始化操作。在完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）。
    开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。

    2.doFilter
        doFilter(ServletRequest request, ServletResponse response, FilterChain chain)方法是一个Filter的核心，这个方法完成实际的
    过滤操作。当客户请求访问与过滤器关联的URL的时候，Servlet过滤器将先执行doFilter方法。FilterChain参数用于访问后续过滤器。
    
    3.destroy
        Filter对象创建后会驻留在内存，当web应用移除或服务器停止时才销毁。在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅
    执行一次。在这个方法中，可以释放过滤器使用的资源。
  
  
#### Filter是如何进行拦截的
Filter接口中有一个doFilter方法，当开发人员编写好Filter，并配置对哪个web资源进行拦截后，Web服务器每次在调用web资源的service方法之前，
都会先调用一下filter的doFilter方法，因此，在该方法内编写代码可达到如下目的：
    
    调用目标资源之前，让一段代码执行。
    是否调用目标资源（即是否让用户访问web资源）。
    web服务器在调用doFilter方法时，会传递一个filterChain对象进来，filterChain对象是filter接口中最重要的一个对象，它也提供了一个
    doFilter方法，开发人员可以根据需求决定是否调用此方法，调用该方法，则web服务器就会调用web资源的service方法，即web资源就会被访问，
    否则web资源不会被访问。

#### Filter链
    在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。
    web服务器根据Filter在web.xml文件中的注册顺序，决定先调用哪个Filter，当第一个Filter的doFilter方法被调用时，web服务器会创建一个代
    表Filter链的FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查
    FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源。
   
#### Filter配置说明
    
    <filter>指定一个过滤器。
    <filter-name>用于为过滤器指定一个名字，该元素的内容不能为空。
    <filter-class>元素用于指定过滤器的完整的限定类名。
    <init-param>元素用于为过滤器指定初始化参数，它的子元素<param-name>指定参数的名字，<param-value>指定参数的值。
    在过滤器中，可以使用FilterConfig接口对象来访问初始化参数。
    <filter-mapping>元素用于设置一个 Filter 所负责拦截的资源。一个Filter拦截的资源可通过两种方式来指定：Servlet 名称和资源访问的请求
    路径
    <filter-name>子元素用于设置filter的注册名称。该值必须是在<filter>元素中声明过的过滤器的名字
    <url-pattern>设置 filter 所拦截的请求路径(过滤器关联的URL样式)
    <servlet-name>指定过滤器所拦截的Servlet名称。
    <dispatcher>指定过滤器所拦截的资源被 Servlet 容器调用的方式，可以是REQUEST,INCLUDE,FORWARD和ERROR之一，默认REQUEST。用户可以
    设置多个<dispatcher>子元素用来指定 Filter 对资源的多种调用方式进行拦截。
    <dispatcher>子元素可以设置的值及其意义
    REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么
    该过滤器就不会被调用。
    INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
    FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
    ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。
    
#### FilterConfig接口说明
    FilterConfig是Filter的一个重要的接口，用户可以通过它获取Filter的初始化参数信息和Servlet的上下文对象的引用。当Filter的init()方法被
    调用时，FilterConfig会作为一个参数传递给Filter对象。FilterConfig接口有以下几个方法：
        public String getFilterName();//获取Filter的名称，可在web.xml配置文件中配置<filter-name>指定。
        public ServletContext getServletContext();//获取Servlet的上下文对象的引用。
        public String getInitParameter(String name);//获取对应的初始化参数值，没有则返回null。可通过<init-param>进行配置。
        public Enumeration<String> getInitParameterNames();//获取所有初始化参数名的枚举。

#### 创建Filter的步骤
1.编写一个Filter实现Filter接口,实现doFilter方法
    
        import javax.servlet.*;
        import java.io.IOException;
        
        public class MyFilter implements Filter {
        
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                //通过FilterConfig可以获取Filter的初始化参数信息和Servlet的上下文对象的引用。
            }
        
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                System.out.println("MyFilter doFilter...");
                //让目标执行，放行
                chain.doFilter(request, response);
            }
        
            @Override
            public void destroy() {
        
            }
        }
        
2.在web.xml文件中配置Filter

        <!--过滤器配置-->
        <filter>
          <filter-name>myFilter</filter-name>
          <filter-class>com.java.summary.web.filter.MyFilter</filter-class>
          <!--配置Filter的初始化参数-->
          <init-param>
            <param-name>last name</param-name>
            <param-value>Holmes</param-value>
          </init-param>
          <init-param>
            <param-name>first name</param-name>
            <param-value>Sherlock</param-value>
          </init-param>
        </filter>
        <filter-mapping>
          <filter-name>myFilter</filter-name>
          <url-pattern>/myServlet</url-pattern>
        </filter-mapping>
        

#### Filter的应用案例

##### 使用Filter完成用户免登录功能
    
一、通过一个Filter完成一个用户免登录功能，首先需要了解Session机制与Cookie机制在用户登录过程中所起到的作用。
        
    参考博文：http://www.cnblogs.com/zhouhbing/p/4204132.html
    
    简单的说：Session和Cookie的作用就是分别在服务端和客户端保存用户的会话信息。
        
二、实现过程
    
1.处理用户登录请求的Servlet
        
    import javax.servlet.ServletException;
    import javax.servlet.http.*;
    import java.io.IOException;
    
    public class LoginServlet extends HttpServlet {
    
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
            String name = req.getParameter("name");
            String pwd = req.getParameter("pwd");
            if("holmes".equals(name) && "holmes".equals(pwd)) {
    
                HttpSession session = req.getSession();
                session.setAttribute("name", name);
    
                //用户点击记住登录功能则将信息记录至Cookie中
                if("true".equals(req.getParameter("autoLogin"))) {
    
                    Cookie cookie = new Cookie("autoLogin", name + "#" + pwd);
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24 * 90);
                    resp.addCookie(cookie);
                }
                System.out.println("登录成功");
                resp.sendRedirect("/login/success.jsp");
            } else {
                req.setAttribute("msg", "用户名或密码错误！");
                req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
            }
        }
    
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req, resp);
        }
    }
2.对用户请求进行拦截的Filter
    
    import javax.servlet.*;
    import javax.servlet.http.Cookie;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    public class LoginFilter implements Filter {
    
        private FilterConfig filterConfig;
    
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("LoginFilter init ...");
            this.filterConfig = filterConfig;
        }
    
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
    
            HttpServletRequest req = (HttpServletRequest) request;
            String uri = req.getRequestURI();
            //以下请求不做拦截处理
            if (uri.indexOf("login.do") >= 0 || uri.indexOf("login.jsp") >= 0 || uri.indexOf("favicon.ico") >= 0
                    || uri.indexOf("index.jsp") > 0 || "/".equals(uri)) {
                chain.doFilter(request, response);
                return;
            }
            HttpServletResponse resp = (HttpServletResponse) response;
            //通过Session查看当前会话信息，判断用户是否已经登录
            if (null != req.getSession().getAttribute("name")) {
                System.out.println("用户已登录");
                chain.doFilter(request, response);
                return;
            } else {
                System.out.println("用户未登录，正在查看是否存在自动登录信息");
                //查看是否含有自动登录Cookie
                Cookie cookie = findCookie(req.getCookies(), "autoLogin");
                if (null == cookie) {
                    // 没有登录信息，跳转至登录页面
                    System.out.println("没有用户自动登录信息，跳转至登录页面");
                    resp.sendRedirect("/login/login.jsp");
                    return;
                } else {
                    String value = cookie.getValue();
                    //检查value格式是否正确
                    if (null == value || !value.matches(".*.#.*.")) {
                        System.out.println("cookie数据格式不正确，跳转至登录页面");
                        resp.sendRedirect("/login/login.jsp");
                        return;
                    }
    
                    String[] values = cookie.getValue().split("#");
    
                    String name = values[0];
                    String pwd = values[1];
                    //存在用户登录信息
                    if ("holmes".equals(name) && "holmes".equals(pwd)) {
                        System.out.println("完成用户自动登录");
                        req.getSession().setAttribute("name", name);
                        chain.doFilter(request, response);
                    } else {
                        //自动登录信息有误
                        System.out.println("用户自动登录信息有误，跳转至登录页面");
                        resp.sendRedirect("/login/login.jsp");
                    }
                }
            }
        }
    
        private Cookie findCookie(Cookie[] cookies, String name) {
            if (null == cookies) {
                return null;
            }
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
            return null;
        }
    
        @Override
        public void destroy() {
    
        }
    }

    
3.在web.xml文件中配置对应的Servlet与Filter
    
     <!--实现自动登录的Filter -->
      <filter>
        <filter-name>autoLoginFilter</filter-name>
        <filter-class>com.java.summary.web.filter.login.LoginFilter</filter-class>
      </filter>
      <filter-mapping>
        <filter-name>autoLoginFilter</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>
    
      <!--处理登录的Servlet-->
      <servlet>
        <servlet-name>loginServlet</servlet-name>
        <servlet-class>com.java.summary.web.servlet.login.LoginServlet</servlet-class>
      </servlet>
      <servlet-mapping>
        <servlet-name>loginServlet</servlet-name>
        <url-pattern>/login.do</url-pattern>
      </servlet-mapping>
    
    
##### 使用Filter处理全站乱码问题
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

