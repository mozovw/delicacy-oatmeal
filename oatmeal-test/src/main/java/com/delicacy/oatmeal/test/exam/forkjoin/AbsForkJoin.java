package com.delicacy.oatmeal.test.exam.forkjoin;

import java.util.concurrent.RecursiveTask;

public  class AbsForkJoin extends RecursiveTask<Long>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5153604270851149565L;
	//阙值
	public final static long THRESHOLD = 100000000;
    private long start;
    private long end;
    
    public AbsForkJoin(long s, long e) {
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
            AbsForkJoin leftTask = new AbsForkJoin(start, mid);
            AbsForkJoin rightTask = new AbsForkJoin(mid+1, end);
            leftTask.fork();
            rightTask.fork();
            sum += leftTask.join();
            sum += rightTask.join();
        }
        return sum;
    }
    
}