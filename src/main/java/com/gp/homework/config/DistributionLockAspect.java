package com.gp.homework.config;

import cn.hutool.core.util.StrUtil;
import com.common.annotation.DistributionLockLisenor;
import com.gp.homework.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 *
 * 分布式锁切面
 * Create by Wayne on 2020/8/7
 */

@Aspect
@Component
@Slf4j
public class DistributionLockAspect {

    @Autowired
    RedisUtil redisUtil ;

    @Pointcut("@annotation(distributionLockLisenor)")
    public void pointCut(DistributionLockLisenor distributionLockLisenor){}


    @Around(value = "@annotation(distributionLockLisenor)")
    public Object around(ProceedingJoinPoint pjp, DistributionLockLisenor distributionLockLisenor) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;

        String functionName = distributionLockLisenor.functionName() ;
        int inSecond =distributionLockLisenor.timeInSecond() ;

        String path = request.getServletPath() ;
        String requestId = UUID.randomUUID().toString() ;

        String lockKey = StrUtil.format("{}{}",functionName,path) ;

        Object obj ;
        for(;;){

            if(redisUtil.tryLock(lockKey,requestId,inSecond*1000)){
                try{
                   obj =  pjp.proceed() ;
                }
                finally {
                    log.info("线程{}成功获得了锁{}，执行完毕后释放{}::::: ",
                            Thread.currentThread().getName(),lockKey,
                            redisUtil.releaseLock(lockKey,requestId) == true ?"成功":"失败");
                }
                break ;
            }
        }
        return obj ;
    }





}
