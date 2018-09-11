package com.delicacy.oatmeal.cache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author yutao
 * @create 2018-09-11 17:12
 **/
@Component
@CacheConfig(cacheNames = "cacheService")
public class CacheService {


    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public String uuid(String uuid) {
        long l = System.currentTimeMillis();
        return uuid + "_" + l;
    }

}
