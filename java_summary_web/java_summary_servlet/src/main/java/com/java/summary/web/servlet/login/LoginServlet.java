package com.java.summary.web.servlet.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");

        if ("holmes".equals(uname) && "sherlock".equals(pwd)) {
            System.out.println("uname = " + uname + ",pwd = " + pwd);
            System.out.println("登录成功");
            //将用户信息加入值session中
            HttpSession session = req.getSession();
            session.setAttribute("name", uname);

            req.getRequestDispatcher("servlet/login/success.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("servlet/login/login.jsp");
        }

    }
}
