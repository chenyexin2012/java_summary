package com.java.summary.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        HttpSession session = req.getSession();
        ServletContext context = req.getServletContext();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("在" + context + "中添加属性：holmes->holmes");
        context.setAttribute("holmes", "holmes");
        System.out.println("在" + req + "中添加属性：holmes->holmes");
        req.setAttribute("holmes", "holmes");
        System.out.println("在" + session + "中添加属性：sherlock->sherlock");
        session.setAttribute("sherlock", "sherlock");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("改变" + context + "中的属性：holmes->sherlock");
        context.setAttribute("holmes", "sherlock");
        System.out.println("改变" + req + "中的属性：holmes->sherlock");
        req.setAttribute("holmes", "sherlock");
        System.out.println("改变" + session + "中的属性：sherlock->holmes");
        session.setAttribute("sherlock", "holmes");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("移除" + context + "中的属性：holmes->sherlock");
        context.removeAttribute("holmes");
        System.out.println("移除" + req + "中的属性：holmes");
        req.removeAttribute("holmes");
        System.out.println("移除" + session + "中的属性：sherlock");
        session.removeAttribute("sherlock");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
