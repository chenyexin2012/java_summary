package com.java.summary.web.servlet.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String message = req.getParameter("message");
        if ("holmes".equals(name) && "holmes".equals(pwd)) {

            HttpSession session = req.getSession();
            session.setAttribute("name", name);
            session.setAttribute("message", message);
            //用户点击记住登录功能则将信息记录至Cookie中
            if ("true".equals(req.getParameter("autoLogin"))) {

                Cookie cookie = new Cookie("autoLogin", name + "#" + pwd);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 90);
                resp.addCookie(cookie);
            }
            System.out.println("登录成功");
            resp.sendRedirect("/login/success.jsp");
        } else {
            req.setAttribute("msg", "用户名或密码错误！");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
