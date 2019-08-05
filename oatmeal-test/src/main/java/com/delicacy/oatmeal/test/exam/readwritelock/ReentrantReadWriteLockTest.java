package com.delicacy.oatmeal.test.exam.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private int count = 1;
	
	public static void main(String[] args) {
		final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
				//exam.put(Thread.currentThread());
			};
		}.start();

		new Thread() {
			public void run() {
				//exam.get(Thread.currentThread());
				test.put(Thread.currentThread());

			};
		}.start();

	}

	public void get(Thread thread) {
		rwl.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName() + "正在进行读操作");
				System.out.println(count);
			}
			System.out.println(thread.getName() + "读操作完毕");
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	
	public void put(Thread thread) {
		rwl.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 10) {
				//System.out.println(thread.getName() + "正在进行write操作");
				count++;
			}
			System.out.println(thread.getName() + "write操作完毕");
		} finally {
			rwl.writeLock().unlock();
		}
	}
}