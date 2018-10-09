### 什么是监听器
    
        监听器是一个实现了特定接口的程序，用于监听另一个对象的方法调用或者属性改变，当被监听的对象发生了上述变化时，监听器会执行对应的某
    种方法。

#### JavaWeb中的监听器

##### 监听器的分类

    1.按监听对象分类
    
        .1 ServletContext对象监听器
        .2 HttpSession对象监听器
        .3 ServletRequest对象监听器
    
    2.按监听事件分类
        
        .1 监听对象的创建与销毁 -->MyServletContextListener、MyHttpSessionListener、MyServletRequestListener
        .2 监听对象中的属性的变更 -->MyServletContextAttributeListener、MyHttpSessionAttributeListener、
            MyServletRequestAttributeListener
        .3 监听绑定至Session的某个对象的状态 -->MyHttpSessionBindingListenerBean、MyHttpSessionActivationListenerBean

##### 如何创建一个监听器
    
    1.实现对应的Listener接口，如：
        
        import javax.servlet.ServletContextEvent;
        import javax.servlet.ServletContextListener;
        
        public class MyServletContextListener implements ServletContextListener {
        
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.out.println("MyServletContextListener contextInitialized");
            }
        
            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("MyServletContextListener contextDestroyed");
            }
        }
    
    2.在web.xml配置文件中配置监听器
    
        <listener>
        <description>ServletContext监听器</description>
        <listener-class>com.java.summary.web.listener.example.MyServletContextListener</listener-class>
        </listener>

##### 使用监听器监听对象的创建和销毁

        实现ServletContextListener、HttpSessionListener、ServletRequestListener接口中的方法，并在web.xml文件中进行相关配置。
    
##### 使用监听器监听对象中属性变更
    
        实现MyServletContextAttributeListener、MyHttpSessionAttributeListener、MyServletRequestAttributeListener接口中的方
    法，并在web.xml文件中进行相关配置。
    
##### 使用监听器监听与Session绑定的对象

        为了帮助一个JavaBean对象了解自己在Session中的状态，Servlet在规范中定义了两个特殊的接口HttpSessionBindingListener和
    HttpSessionActivationListener，并且这两个监听器无需在web.xml文件中进行配置。
        
     1.HttpSessionBindingListener
        
        接口中提供了valueBound(HttpSessionBindingEvent event)和valueUnbound(HttpSessionBindingEvent event)，当实现了
    HttpSessionBindingListener接口的Bean对象在绑定至session对象时或是解除绑定时会分别调用valueBound和valueUnbound方法，进行对应的
    操作。
    
     
     2.HttpSessionActivationListener
     
        接口中提供了sessionWillPassivate(HttpSessionEvent se)和sessionDidActivate(HttpSessionEvent se)方法，分别用于实现了
    HttpSessionBindingListener接口（同时还必须实现Serializable接口）的Bean对象在随session一起被序列化或反序列化时被调用。
    
        注：在WebRoot\META-INF文件夹下创建一个context.xml文件，内容如下：
            <Context>
                <Manager className="org.apache.catalina.session.PersistentManager" maxIdleSwap="1">
                    <Store className="org.apache.catalina.session.FileStore" directory="文件路径"/>
                </Manager>
            </Context>
        可以用与在1分钟后将session对象序列化到对应的文件中，并在再次访问时被反序列化得到session对象。


#### 监听器的应用

##### 使用监听器监听当前在线人数

    1.实现一个HttpSessionListener监听器用户监听session的创建和销毁。
    2.利用ServletContext对象（application）存储当前在线人数。
    
    
    