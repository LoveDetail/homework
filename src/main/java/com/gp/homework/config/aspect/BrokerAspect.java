package com.gp.homework.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Create by Wayne on 2020/8/10
 */
@Component
@Aspect
@Slf4j
public class BrokerAspect {

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     *
     * execution(public * com.gp.homework.controller.aop.MyTestAOPController.*(..))) 解析
     * public :方法作用于 ，当前描述为public方法才定义切入点
     * * 返回任意类型
     * com.gp.homework.controller.aop.MyTestAOPController 具体切入点的包路径
     * *(..) 任意方法
     *
     */
    @Pointcut("execution(public * com.gp.homework.controller.aop.MyTestAOPController.*(..)))")
    public void BrokerAspect(){

    }

    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame(){
        System.out.println("经纪人正在处理球星赛前事务！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfterGame(){
        System.out.println("经纪人为球星表现疯狂鼓掌！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame(){
        System.out.println("返回通知：经纪人为球星表现疯狂鼓掌！");
    }

    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(){
        System.out.println("异常通知：球迷要求退票！");
    }
}
