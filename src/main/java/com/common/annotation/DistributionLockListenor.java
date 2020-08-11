package com.common.annotation;

import java.lang.annotation.*;


/**
 * @功能描述 防止重复提交标记注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributionLockListenor {

    /**
     * 锁的有效时间，单位为秒，默认值为2
     * @return
     */
    int timeInSecond() default 2;

    /**
     * 需要根据分布式锁执行的业务类型
     * @return
     */
    String functionName() default "" ;

}
