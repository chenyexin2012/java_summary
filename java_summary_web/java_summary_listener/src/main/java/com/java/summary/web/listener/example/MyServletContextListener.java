package com.java.summary.web.listener.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {

        System.out.println("监听ServletContext对象的创建与销毁的监听器");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyServletContextListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyServletContextListener contextDestroyed");
    }
}
