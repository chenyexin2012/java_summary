package com.java.summary.web.listener.count;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;

public class CountNumberOfOnlineListener implements HttpSessionListener {

    private AtomicLong count = new AtomicLong(0);


    @Override
    public void sessionCreated(HttpSessionEvent se) {

        ServletContext context = se.getSession().getServletContext();

        long current = count.incrementAndGet();
        System.out.println("当前在线人数为：" + current);
        System.out.println(se.getSession());

        context.setAttribute("currentUserNumber", current);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        ServletContext context = se.getSession().getServletContext();

        long current = count.decrementAndGet();
        System.out.println("当前在线人数为：" + current);
        System.out.println(se.getSession());

        context.setAttribute("currentUserNumber", current);
    }
}
