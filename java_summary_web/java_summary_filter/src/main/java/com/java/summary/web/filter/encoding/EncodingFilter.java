package com.java.summary.web.filter.encoding;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("EncodingFilter init ...");
        String initCharset = filterConfig.getInitParameter("charset");
        System.out.println("initCharset:" + initCharset);
        if (null != initCharset && !"".equals(initCharset)) {
            this.charset = initCharset;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setContentType("text/html;charset=" + charset);
        // 增强request对象
        chain.doFilter(new EncodingRequest((HttpServletRequest) request, this.charset), response);
    }

    @Override
    public void destroy() {

    }
}
