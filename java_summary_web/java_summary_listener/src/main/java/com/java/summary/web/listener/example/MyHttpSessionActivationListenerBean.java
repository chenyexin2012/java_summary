package com.java.summary.web.listener.example;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class MyHttpSessionActivationListenerBean implements HttpSessionActivationListener, Serializable {

    private static final long serialVersionUID = -123074550116086403L;

    private String name;

    private String value;

    public MyHttpSessionActivationListenerBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {

        HttpSession session = se.getSession();

        System.out.println(this + "与" + session + "一起被序列化");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {

        HttpSession session = se.getSession();

        System.out.println(this + "与" + session + "一起被反序列化");
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
        return "MyHttpSessionActivationListenerBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
