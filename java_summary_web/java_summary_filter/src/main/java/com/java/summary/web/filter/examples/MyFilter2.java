package com.java.summary.web.filter.examples;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String fileName = filterConfig.getFilterName();
        System.out.println(fileName + " init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter2 doFilter...");
        //让目标执行，放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
