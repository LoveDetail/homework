package com.gp.homework.config.aspect;

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
    @Around(value = "@annotation(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp,NoRepeatSubmit noRepeatSubmit) throws Throwable{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
        Assert.notNull(request,"request can not null !!!");

        String flag = request.getHeader("Authorization") ;

        String key = StrUtil.format("{}{}",request.getSession().getId(),request.getServletPath()) ;
        String requestId = UUID.randomUUID().toString() ;

        if(redisUtil.tryLock(key,requestId,noRepeatSubmit.timeInSecond() * 100000)){
            log.info("tryLock success,key=[{}],valueUUID = [{}]",key,requestId) ;
            Object result ;
            try{
               result = pjp.proceed() ;
            }
            finally {
                log.info("releaseLock {},key=[{}],valueUUID = [{}]", redisUtil.releaseLock(key, requestId) ? "success" : "faild",key,requestId);
            }
            return result ;
        }
        else {
            log.info("tryLock fail,key = [{}]",key);
            return "加锁请求重复，稍后在试！！！" ;
        }
    }

}
