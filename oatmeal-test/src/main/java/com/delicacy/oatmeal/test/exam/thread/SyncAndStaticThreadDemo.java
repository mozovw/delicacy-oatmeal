package com.delicacy.oatmeal.test.exam.thread;


/**
 * 就是为了证明类方法和方法 加上synchronized不互斥
 * static修饰的方法 是类方法
 * synchronized 修饰方法是为了线程同步
 * 	
 * Waits at most {@code millis} milliseconds for this thread to die.
 * public final synchronized void join(long millis)
 * join 等待毫秒销毁
 * join 方法本身就用到synchronized的，所以线程互斥
 * 
 * @author Administrator
 *
 */
public class SyncAndStaticThreadDemo {
    public static synchronized void staticFunction()
            throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("Static function running...");
        }
    }
    /*
     * sssss
     * @throws InterruptedException
     */
    public synchronized void function() throws InterruptedException {
        for (int i = 0; i <3; i++) {
            Thread.sleep(1000);
            System.out.println("function running...");
        }
    }
 
    public static void main(String[] args) {
        final SyncAndStaticThreadDemo syncAndStaticThreadDemo = new SyncAndStaticThreadDemo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    staticFunction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
 
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syncAndStaticThreadDemo.function();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
 
        thread1.start();
      
        thread2.start();
    }
}