package com.gp.homework.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Create by Wayne on 2020/8/4
 */

//@Component
@WebFilter(filterName="myFilter",urlPatterns={"/*"})
public class MyFilter implements Filter {

    /**
     * 该方法在容器启动初始化过滤器时被调用，
     * 它在 Filter 的整个生命周期只会被调用一次。
     * 「注意」：这个方法必须执行成功，否则过滤器会不起作用。
     *
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("Filter 前置");
    }


    /**
     * 容器中的每一次请求都会调用该方法， FilterChain 用来调用下一个过滤器 Filter
     *
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("Filter 处理中");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * 当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器 Filter 的整个生命周期也只会被调用一次
     */
    @Override
    public void destroy() {

//        System.out.println("Filter 后置");
    }
}
