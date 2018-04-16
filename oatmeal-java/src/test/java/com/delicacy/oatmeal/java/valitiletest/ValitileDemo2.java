package com.delicacy.oatmeal.java.valitiletest;

import java.util.concurrent.CountDownLatch;

/**
 * 测试volatile不能保证对变量的操作是原子性
 *
 * @author zyt
 * @create 2018-04-15 12:53
 **/
public class ValitileDemo2 extends Thread{
    public volatile int inc = 0;

    public /*synchronized*/ void increase() {
        inc++;
    }

    public static void main(String[] args) {
        process2();
    }

    private static void process1() {
        final ValitileDemo2 test = new ValitileDemo2();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++)
                    test.increase();
            }).start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

    private static void process2() {
        final int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        final ValitileDemo2 test = new ValitileDemo2();
        for(int i=0; i<count; i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    test.increase();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();  //保证前面的线程都执行完
        } catch (InterruptedException e) {
        }
        System.out.println(test.inc);
    }

    private static void process3() {
        final ValitileDemo2 test = new ValitileDemo2();

        Thread thread = new Thread(() -> {
            for (int j = 0; j < 1000; j++)
                test.increase();
        });
        Thread thread2 = new Thread(() -> {
            for (int j = 0; j < 1000; j++)
                test.increase();
        });
        thread.start();
        thread2.start();

        try {
            thread.join(); //保证前面的线程都执行完
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.inc);
    }

}
