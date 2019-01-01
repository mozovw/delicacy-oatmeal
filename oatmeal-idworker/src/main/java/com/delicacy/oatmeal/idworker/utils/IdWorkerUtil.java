package com.delicacy.oatmeal.idworker.utils;


import com.delicacy.oatmeal.idworker.idworker.IdWorker;


public class IdWorkerUtil {

    static private IdWorker idWorker;

    public static void setIdWorker(IdWorker idWorker) {
        IdWorkerUtil.idWorker = idWorker;
    }

    public static long nextId() {
        return idWorker.nextId();
    }
}