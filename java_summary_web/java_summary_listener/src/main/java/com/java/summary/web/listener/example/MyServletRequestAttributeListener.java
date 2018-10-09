package com.java.summary.web.listener.example;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpServletRequest;

public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

    public MyServletRequestAttributeListener() {

        System.out.println("监听ServletRequest对象属性变更的监听器");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {

        ServletRequest request = srae.getServletRequest();

        String name = srae.getName();
        Object value = srae.getValue();

        System.out.println(request + "对象中添加了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletRequest request = srae.getServletRequest();

        String name = srae.getName();
        Object value = srae.getValue();

        System.out.println(request + "对象中移除了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

        ServletRequest request = srae.getServletRequest();

        String name = srae.getName();

        System.out.println(request + "对象中改变了属性: " + name);
    }
}
