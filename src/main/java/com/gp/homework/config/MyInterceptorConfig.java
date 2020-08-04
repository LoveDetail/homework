package com.gp.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将自定义好的拦截器处理类进行注册，
 * 并通过addPathPatterns、excludePathPatterns等属性设置需要拦截或需要排除的 URL
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        System.out.println("config add success ！！");
//        registry.addInterceptor(new MyInterceptor()).excludePathPatterns("/test/a") ;
    }
}
