package com.delicacy.oatmeal.test.exam.forkjoin;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class ForkJoinTest extends RecursiveTask<Long>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5153604270851149565L;
	//阙值
	public final static long THRESHOLD = 100000000;
    private long start;
    private long end;
    
    public ForkJoinTest(long s, long e) {
        start = s;
        end = e;
    }
    
    @Override
    protected Long compute() {
        long sum = 0L;
        boolean devide = (end - start) <= THRESHOLD;
        if (devide) {
            for (long i=start; i<=end; i++) {
                sum += i;
            }
        } else {
            long mid = (start + end) >> 1;
            ForkJoinTest leftTask = new ForkJoinTest(start, mid);
            ForkJoinTest rightTask = new ForkJoinTest(mid+1, end);
            leftTask.fork();
            rightTask.fork();
            sum += leftTask.join();
            sum += rightTask.join();
            
        }
        return sum;
    }
    
    private long cal(long start, long end) {
        long sum = 0L;
        for (long i=start; i<=end; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
       //2147483647
    	long end = 2000000000;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1,end);
        Future<Long> future = forkJoinPool.submit(task);
        long t1 = System.currentTimeMillis();
        System.out.println(future.get());
        long t2 = System.currentTimeMillis();
        System.out.println(task.cal(1, end));
        long t3 = System.currentTimeMillis();
        
        System.out.println("1:" + (t2-t1));
        System.out.println("2:" + (t3-t2));
        
        Scanner s = new Scanner(System.in);
        int nextInt = s.nextInt();
        
    }
}