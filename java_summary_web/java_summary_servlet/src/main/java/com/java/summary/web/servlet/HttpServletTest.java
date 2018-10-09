package com.java.summary.web.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletTest extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 2785916892507259813L;


    private ServletConfig servletConfig = null;

    public HttpServletTest() {
        System.out.println("HttpServletTest constructor ...");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        System.out.println("HttpServletTest init ...");
        this.servletConfig = config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HttpServletTest doGet ...");

        System.out.println("test request Attribute");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            System.out.println(name + " : " + request.getAttribute(name));
        }
        System.out.println("test request Parameter");
        Map<String, String[]> paramterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = paramterMap.entrySet();
        for (Map.Entry<String, String[]> entry : set) {
            System.out.print(entry.getKey() + " : ");
            for (String str : entry.getValue()) {
                System.out.print(str + "\t");
            }
            System.out.println();
        }

        request.setAttribute("name", "zhangsan");

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HttpServletTest doPost ...");

        // 转发（forward）
        request.getRequestDispatcher("servlet/httpServlet.jsp").forward(request, response);

        // 重定向（redirect）
//		response.sendRedirect("servlet/httpServlet.jsp");
    }
}
