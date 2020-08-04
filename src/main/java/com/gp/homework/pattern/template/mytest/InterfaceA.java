package com.gp.homework.pattern.template.mytest;

import cn.hutool.core.util.StrUtil;

/**
 * Create by Wayne on 2020/6/28
 */
@FunctionalInterface
public interface InterfaceA {

   default String hello(String userName){
       String str = StrUtil.format("==========>   hello {}",userName) ;
       System.out.println(str);
       return str ;
   }

   <T> String toString(T t) ;

}
