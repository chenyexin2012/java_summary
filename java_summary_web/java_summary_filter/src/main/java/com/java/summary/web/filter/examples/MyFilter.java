package com.java.summary.web.filter.examples;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class MyFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("MyFilter init...");
        this.filterConfig = filterConfig;
        // 根据filterConfig获取Filter的初始化参数
        String filterName = this.filterConfig.getFilterName();
        System.out.println("filter name = " + filterName);

        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            System.out.println(name + " = " + this.filterConfig.getInitParameter(name));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter doFilter...");
        //让目标执行，放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
