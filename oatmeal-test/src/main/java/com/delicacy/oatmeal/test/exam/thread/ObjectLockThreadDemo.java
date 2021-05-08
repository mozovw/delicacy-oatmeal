package com.delicacy.oatmeal.test.exam.thread;

public class ObjectLockThreadDemo{
	public static void main(String[] args) {
		demo3();
	}
	/**
	 * 那如何让两个线程按照指定方式有序交叉运行呢？
	 * A 1, B 1, B 2, B 3, A 2, A 3
	 */
	private static void demo3() {
	    final Object lock = new Object();
	    Thread A = new Thread(() -> {
			synchronized (lock) {
				System.out.println("A 1");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("A 2");
				System.out.println("A 3");
			}

		});
	
	    Thread B = new Thread(() -> {
			synchronized (lock) {
				System.out.println("B 1");
				System.out.println("B 2");
				System.out.println("B 3");
				lock.notify();
			}
		});
	
	    A.start();
	    B.start();
	}
}

