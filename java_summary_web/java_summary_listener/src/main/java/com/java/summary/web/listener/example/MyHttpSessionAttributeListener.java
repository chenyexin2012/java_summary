package com.java.summary.web.listener.example;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    public MyHttpSessionAttributeListener() {

        System.out.println("监听HttpSession对象属性变更的监听器");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        String name = event.getName();
        Object value = event.getValue();

        System.out.println(session + "对象中添加了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        String name = event.getName();
        Object value = event.getValue();

        System.out.println(session + "对象中移除了属性: " + name + ", 值为: " + value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        String name = event.getName();

        System.out.println(session + "对象中改变了属性: " + name);
    }
}
