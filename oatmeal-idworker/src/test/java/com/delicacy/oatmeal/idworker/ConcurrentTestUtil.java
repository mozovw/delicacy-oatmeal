package com.delicacy.oatmeal.idworker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 并发测试工具类
 *
 * @author zyt
 * @create 2018-04-27 18:56
 **/
public class ConcurrentTestUtil {

    private static final int THREADNUM = 100;

    public static <T> void test(T t,Consumer<T> consumer,int threadNum){
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i=0;i<threadNum;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                consumer.accept(t);
            }).start();
            countDownLatch.countDown();
        }
        /*while(Thread.activeCount()>1){
            //保证前面的线程都执行完
            Thread.yield();
        }*/
    }

    public static <T> void test(T t,Consumer<T> consumer){
        test(t,consumer,THREADNUM);
    }
    public static void test(Consumer consumer){
        test(null,consumer,THREADNUM);
    }

    public static void test(Consumer consumer,int threadNum){
        test(null,consumer,threadNum);
    }


}
