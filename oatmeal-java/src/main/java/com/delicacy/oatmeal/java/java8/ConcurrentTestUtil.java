package com.delicacy.oatmeal.java.java8;

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
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> void test(T t,Consumer<T> consumer){
        test(t,consumer,THREADNUM);
    }

}
