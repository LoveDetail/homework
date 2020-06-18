package com.gp.homework.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Create by Wayne on 2020/1/14
 */
//@Service
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate ;

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
