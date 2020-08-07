package com.gp.homework.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by Wayne on 2020/8/4
 */


public class MyInterceptor implements HandlerInterceptor{


    /**
     * 这个方法将在请求处理之前进行调用。
     * 「注意」：如果该方法的返回值为false ，将视为当前请求结束，不仅自身的拦截器会失效，还会导致其他的拦截器也不再执行。
     *
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("Interceptor 前置");
        return true;
    }


    /**
     * 只有在 preHandle() 方法返回值为true 时才会执行。
     * 会在Controller 中的方法调用之后，DispatcherServlet 返回渲染视图之前被调用。
     * 「有意思的是」：postHandle() 方法被调用的顺序跟 preHandle() 是相反的，
     * 先声明的拦截器  preHandle() 方法先执行，而postHandle()方法反而会后执行
     *
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("Interceptor 处理中.....");
    }


    /**
     * 只有在 preHandle() 方法返回值为true 时才会执行。在整个请求结束之后，
     * DispatcherServlet 渲染了对应的视图之后执行。
     *
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("Interceptor 后置");
    }
}
