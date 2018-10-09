package com.java.summary.web.filter.login;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter init ...");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        //以下请求不做拦截处理
        if (uri.indexOf("login.do") >= 0 || uri.indexOf("login.jsp") >= 0 || uri.indexOf("favicon.ico") >= 0
                || uri.indexOf("index.jsp") > 0 || "/".equals(uri)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        //通过Session查看当前会话信息，判断用户是否已经登录
        if (null != req.getSession().getAttribute("name")) {
            System.out.println("用户已登录");
            chain.doFilter(request, response);
            return;
        } else {
            System.out.println("用户未登录，正在查看是否存在自动登录信息");
            //查看是否含有自动登录Cookie
            Cookie cookie = findCookie(req.getCookies(), "autoLogin");
            if (null == cookie) {
                // 没有登录信息，跳转至登录页面
                System.out.println("没有用户自动登录信息，跳转至登录页面");
                resp.sendRedirect("/login/login.jsp");
                return;
            } else {
                String value = cookie.getValue();
                //检查value格式是否正确
                if (null == value || !value.matches(".*.#.*.")) {
                    System.out.println("cookie数据格式不正确，跳转至登录页面");
                    resp.sendRedirect("/login/login.jsp");
                    return;
                }

                String[] values = cookie.getValue().split("#");

                String name = values[0];
                String pwd = values[1];
                //存在用户登录信息
                if ("holmes".equals(name) && "holmes".equals(pwd)) {
                    System.out.println("完成用户自动登录");
                    req.getSession().setAttribute("name", name);
                    chain.doFilter(request, response);
                } else {
                    //自动登录信息有误
                    System.out.println("用户自动登录信息有误，跳转至登录页面");
                    resp.sendRedirect("/login/login.jsp");
                }
            }
        }
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        if (null == cookies) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
