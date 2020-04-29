package com.gp.homework.common;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.gp.homework.callback.ClientRequest;

/**
 * Create by Wayne on 2020/4/28
 */
public class CommonTimeCache {
    //设定默认过期时间 1小时
    public static final TimedCache<ClientRequest,Long> TIMED_CACHE = CacheUtil.newTimedCache(60*60*1000);
    static{
        TIMED_CACHE.schedulePrune(1000);  //设定1秒钟检测一次过期
    }
}
