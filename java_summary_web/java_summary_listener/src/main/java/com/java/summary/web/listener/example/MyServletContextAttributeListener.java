package com.java.summary.web.listener.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public MyServletContextAttributeListener() {

        System.out.println("监听ServletContext对象属性变更的监听器");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {

        ServletContext servletContext = event.getServletContext();

        String name = event.getName();
        Object value = event.getValue();

        System.out.println(servletContext + "对象中添加了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {

        ServletContext servletContext = event.getServletContext();

        String name = event.getName();
        Object value = event.getValue();

        System.out.println(servletContext + "对象中移除了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {

        ServletContext servletContext = event.getServletContext();

        String name = event.getName();

        System.out.println(servletContext + "对象中修改了属性: " + name);
    }
}
