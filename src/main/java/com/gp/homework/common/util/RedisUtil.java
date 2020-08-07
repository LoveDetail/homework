package com.gp.homework.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/1/14
 */
@Service
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate ;


    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param uuid 请求标识
     * @param expireMillis 超期时间
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String uuid, int expireMillis) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey,uuid,Duration.ofMillis(expireMillis));
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end" ;
        return (Long)redisTemplate.execute(RedisScript.of(script,Long.class), Arrays.asList(lockKey),requestId) >= 1 ? true : false ;
    }

    /**
     *
     * @param redisEnmu 只允许取值String
     * @param key
     * @param value
     */
    public void setValue(RedisEnmu redisEnmu,String key,Object value){

        switch (redisEnmu){
            case STRING:
                this.redisTemplate.opsForValue().set(key,value); break ;

            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }
    public void setValue(RedisEnmu redisEnmu,String key,Object value,long l){
        switch (redisEnmu){
            case STRING:
                this.redisTemplate.opsForValue().set(key,value,l); break ;
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }
    public void setValue(RedisEnmu redisEnmu,String key,Object value,Duration timeOut){
        switch (redisEnmu){
            case STRING:
                this.redisTemplate.opsForValue().set(key,value,timeOut); break ;
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }
    public void setValue(RedisEnmu redisEnmu,String key,Object value,TimeUnit timeutil,long l){
        switch (redisEnmu){
            case STRING:
                this.redisTemplate.opsForValue().set(key,value,l,timeutil); break ;
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }

    public boolean setBitValue(RedisEnmu redisEnmu,String key,long l,boolean b){
        switch (redisEnmu){
            case STRING:
                return this.redisTemplate.opsForValue().setBit(key,l,b);
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }

    public boolean setIfAbsent(RedisEnmu redisEnmu,String key,Object v){
        switch (redisEnmu){
            case STRING:
                return this.redisTemplate.opsForValue().setIfAbsent(key,v);
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;


        }
    }

    public boolean setIfAbsent(RedisEnmu redisEnmu,String key,Object value,Duration timeOut){
        switch (redisEnmu){
            case STRING:
                return this.redisTemplate.opsForValue().setIfAbsent(key,value,timeOut);
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }

    public boolean setIfAbsent(RedisEnmu redisEnmu,String key,Object value,TimeUnit timeUtil,long l){
        switch (redisEnmu){
            case STRING:
                return this.redisTemplate.opsForValue().setIfAbsent(key,value,l,timeUtil);
            default:
                throw new IllegalArgumentException("不是String类型的操作，请确定RedisEnmu是否正确 !!!") ;
        }
    }




}
