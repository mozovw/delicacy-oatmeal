package com.delicacy.oatmeal.test.exam.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
 * 
 * 创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
 *  在等待线程里调用
 * countDownLatch.await() 方法，进入等待状态，直到计数值变成 0； 在其他线程里，调用
 * countDownLatch.countDown() 方法，该方法会将计数值减小 1； 当其他线程的 countDown() 
 * 方法把计数值变成 0 时，
 * 等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		runDAfterABC();
	}

	private static void runDAfterABC() {
		int worker = 3;
		final CountDownLatch countDownLatch = new CountDownLatch(worker);

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("D is waiting for other three threads");
				try {
					countDownLatch.await();
					System.out.println("All done, D starts working");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

		for (char threadName = 'A'; threadName <= 'C'; threadName++) {
			final String tN = String.valueOf(threadName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(tN + " is working");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println(tN + " finished");
					countDownLatch.countDown();
				}
			}).start();
		}
	}
}
