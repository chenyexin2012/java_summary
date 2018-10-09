package com.java.summary.web.listener.example;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

    public MyServletRequestListener() {

        System.out.println("监听ServletRequest对象的创建与销毁的监听器");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        ServletRequest request = sre.getServletRequest();

        System.out.println(request + " destroyed ...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        ServletRequest request = sre.getServletRequest();

        System.out.println(request + " initialized ...");
    }
}
