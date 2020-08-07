package com.common.annotation;

import java.lang.annotation.*;


/**
 * @功能描述 防止重复提交标记注解
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
@Documented
public @interface NoRepeatSubmit {

    /**
     * 若加注解的入参时基本数据类型（int,long）或String时，fieldName无效<br>
     * 若注解的参数是自定义对象时，请注意一下几点：<br>
     *         1.确保定义有相应属性public修饰的get方法<br>
     *         2.get方法的返回参数是基本的数据类型或String<br>
     *         3.get方法的返回值不为空<br>
     * 否则，加锁失败.
     * @return
     */
    String[] fieldName() default {};
    /**
     * 锁的有效时间，单位为秒，默认值为1
     * @return
     */
    int timeInSecond() default 2;
    /**
     * 加锁，锁已被其它请求获取时，直接返回重复提交，codeName指定返回对象的返回码对应的属性,默认值'code'
     * @return
     */
    String codeName() default "code";
    /**
     * 加锁，锁已被其它请求获取时，直接返回重复提交，msgName指定返回对象的返回信息对应的属性,默认值'msg'
     * @return
     */
    String msgName() default "msg";
    /**
     * 加锁，锁已被其它请求获取时，直接返回重复提交，code指定返回对象的返回码对应的值,默认值'09'
     * @return
     */
    String code() default "09";
    /**
     * 加锁，锁已被其它请求获取时，直接返回重复提交，msg指定返回对象的返回码对应的值,默认值'重复提交'
     * @return
     */
    String msg()  default "重复提交";
    /**
     * 注解作用与方法时，指定参数在参数列表中的索引
     */
    int paramIndex() default 0;
}
