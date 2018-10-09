package com.java.summary.web.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletTest implements Servlet {

    private ServletConfig servletConfig = null;

    public ServletTest() {
        System.out.println("ServletTest constructor ...");
    }

    public void init(ServletConfig config) throws ServletException {

        System.out.println("ServletTest init ...");
        this.servletConfig = config;
    }

    public ServletConfig getServletConfig() {

        return this.servletConfig;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("ServletTest service ...");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println("test request Attribute");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            System.out.println(name + " : " + request.getAttribute(name));
        }
        System.out.println("test request Parameter");
        Map<String, String[]> paramterMap = request.getParameterMap();
        Set<Entry<String, String[]>> set = paramterMap.entrySet();
        for (Entry<String, String[]> entry : set) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        HttpSession session = request.getSession();

        session.setAttribute("name", "holmes");
        session.setAttribute("address", "baker221b");

        request.setAttribute("name", "zhangsan");

        // 转发（forward）
        request.getRequestDispatcher("servlet/servlet.jsp").forward(request, response);

        // 重定向（redirect）
//		response.sendRedirect("servlet/servlet.jsp");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("ServletTest destroy ...");
    }
}
