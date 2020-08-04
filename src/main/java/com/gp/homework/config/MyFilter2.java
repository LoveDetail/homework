package com.gp.homework.config;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Create by Wayne on 2020/8/4
 */
@WebFilter(filterName = "MyFilter2",urlPatterns="/*")
@Order(2)
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter 前置====2");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 处理====2");
    }

    @Override
    public void destroy() {
        System.out.println("Filter 销毁====2");
    }
}
