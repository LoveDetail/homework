package com.gp.homework.config;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.common.annotation.NoRepeatSubmit;
import com.gp.homework.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Create by Wayne on 2020/8/6
 */

@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    @Autowired
    RedisUtil redisUtil ;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit){}


//    @Around("pointCut(noRepeatSubmit)")
    @Around(value = "@annotation(noRepeatSubmit)") //around 与 下面参数名around对应
    public Object around(ProceedingJoinPoint pjp,NoRepeatSubmit noRepeatSubmit) throws Throwable{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
        Assert.notNull(request,"request can not null !!!");

        String key = StrUtil.format("{}{}",request.getSession().getId(),request.getServletPath()) ;
        String uuid = UUID.randomUUID().toString() ;

        if(redisUtil.tryLock(key,uuid,noRepeatSubmit.timeInSecond() * 100000000)){
            ThreadUtil.execute(()->log.info("tryLock success,key=[{}],valueUUID = [{}]",key,uuid));

            Object result ;
            try{
               result = pjp.proceed() ;
            }
            finally {
                redisUtil.releaseLock(key,uuid) ;
                ThreadUtil.execute(()->log.info("releaseLock success,key=[{}],valueUUID = [{}]",key,uuid));
            }
            return result ;
        }

        log.info("tryLock fail,key = [{}]",key);
        return "加锁请求重复，稍后在试！！！" ;
    }

}
