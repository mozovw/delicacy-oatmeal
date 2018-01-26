package com.delicacy.oatmeal.test.exam.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
 * 
 * 为了实现线程间互相等待这种需求，我们可以利用 CyclicBarrier 数据结构，它的基本用法是：
 * 
 * 先创建一个公共 CyclicBarrier 对象，设置同时等待的线程数，CyclicBarrier cyclicBarrier = new
 * CyclicBarrier(3); 这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用
 * cyclicBarrier.await(); 即可开始等待别人； 当指定的同时等待的线程数都调用了
 * cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才同时继续执行。
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		runABCWhenAllReady();
	}

	private static void runABCWhenAllReady() {
		//int runner = 3;
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

		final Random random = new Random();
		for (char runnerName = 'A'; runnerName <= 'C'; runnerName++) {
			final String rN = String.valueOf(runnerName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					long prepareTime = random.nextInt(10000) + 100;
					System.out.println(rN + " is preparing for time: "
							+ prepareTime);
					try {
						Thread.sleep(prepareTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						System.out.println(rN
								+ " is prepared, waiting for others");
						cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}

					System.out.println(rN + " starts running"); // 所有运动员都准备好了，一起开始跑
				}
			}).start();
		}
	}
}
