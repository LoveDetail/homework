package com.common.annotation;

import java.lang.annotation.*;


/**
 * @功能描述 防止重复提交标记注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {


    /**
     * 锁的有效时间，单位为秒，默认值为2
     *
     * @return
     */
    int timeInSecond() default 2;

}
