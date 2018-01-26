package com.delicacy.oatmeal.test.exam.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        //如果关闭后所有任务都已完成，则返回 true。
        //注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。 
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        
    }
}