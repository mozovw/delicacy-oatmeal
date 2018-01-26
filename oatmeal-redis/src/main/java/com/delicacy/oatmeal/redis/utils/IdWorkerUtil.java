package com.delicacy.oatmeal.redis.utils;

import com.delicacy.oatmeal.redis.cache.IdWorker;

/**
 * ID 生成工具
 * <p>
 * Title: IdWorkerUtils<br>
 * Description: IdWorkerUtils<br>
 * CreateDate:2017年9月25日 下午1:34:01
 */
public class IdWorkerUtil {

    static private IdWorker idWorker;

    public static void setIdWorker(IdWorker idWorker) {
        IdWorkerUtil.idWorker = idWorker;
    }

    public static long nextId() {
        return idWorker.nextId();
    }
}