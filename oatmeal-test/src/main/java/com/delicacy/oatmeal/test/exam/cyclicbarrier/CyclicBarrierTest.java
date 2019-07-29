package com.delicacy.oatmeal.test.exam.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yutao
 * @create 2019-03-19 17:21
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N,new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());
            }
        });
        for(int i=0;i<N;i++)
            new Writer(barrier).start();


        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }

    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"准备好了");
            try {
                Thread.sleep(5000); //以睡眠来模拟线程需要预定写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"线程写入完毕，继续处理其他任务，比如数据操作");
        }
    }
}
