package com.delicacy.oatmeal.test.exam.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 子线程完成某件任务后，把得到的结果回传给主线程 实际的开发中，我们经常要创建子线程来做一些耗时任务，然后把任务执行结果回传给主线程使用，这种情况在
 * Java 里要如何实现呢？
 * 
 * 可以看出 Callable 最大区别就是返回范型 V 结果。
 * 
 * 那么下一个问题就是，如何把子线程的结果回传回来呢？在 Java 里，有一个类是配合 Callable 使用的：FutureTask，不过注意，它获取结果的
 * get 方法会阻塞主线程。
 */
public class CallableDemo {

	public static void main(String[] args) {
		doTaskWithResultInWorker();
	}

	private static void doTaskWithResultInWorker() {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("Task starts");
				Thread.sleep(1000);
				int result = 0;
				for (int i = 0; i <= 100; i++) {
					result += i;
				}
				System.out.println("Task finished and return result");
				return result;
			}
		};

		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		new Thread(futureTask).start();

		try {
			System.out.println("Before futureTask.get()");
			System.out.println("Result: " + futureTask.get());
			System.out.println("After futureTask.get()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
