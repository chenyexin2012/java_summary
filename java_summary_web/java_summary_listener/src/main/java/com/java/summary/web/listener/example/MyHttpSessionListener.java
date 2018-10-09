package com.java.summary.web.listener.example;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

    public MyHttpSessionListener() {

        System.out.println("监听HttpSession对象的创建与销毁的监听器");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        System.out.println(session + " created ...");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();

        System.out.println(session + " destroyed ...");
    }
}
