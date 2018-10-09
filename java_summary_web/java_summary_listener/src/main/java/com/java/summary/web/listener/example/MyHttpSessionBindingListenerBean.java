package com.java.summary.web.listener.example;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyHttpSessionBindingListenerBean implements HttpSessionBindingListener {

    private String name;

    private String value;

    public MyHttpSessionBindingListenerBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        System.out.println(this + "被" + session + "绑定");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();

        System.out.println(this + "与" + session + "解除绑定");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyHttpSessionBindingListenerBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
