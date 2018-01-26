package com.delicacy.oatmeal.test.exam.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * Semaphore可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。假如有一个需求，
 * 要读取几万个文件的数据，因为都是IO密集型任务，
 * 我们可以启动几十个线程并发的读取
 * ，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，
 * 这时我们必须控制只有十个线程同时获取数据库连接保存数据
 * ，否则会报错无法获取数据库连接。这个时候，我们就可以使用Semaphore来做流控
 * 
 * @author Administrator
 *
 */
public class SemaphoreTest {

     public static void main(String[] args) {  
        // 线程池 
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问 
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个客户端访问 
        for (int index = 0; index < 20; index++) {
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                    	// 我要是限制线程创建的个树，可以一样的效果 newFixedThreadPool
                        // 获取许可 
                        semp.acquire();  
                        System.out.println("Accessing: " + NO);  
                        Thread.sleep((long) (Math.random() * 10000));  
                        // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                        semp.release();  
                    } catch (InterruptedException e) {  
                    }  
                }  
            };  
            exec.execute(run);  
        }  
        // 退出线程池 
        exec.shutdown();  
    }  
}