package com.delicacy.oatmeal.test.exam.atomic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Counter {
	private AtomicInteger count = new AtomicInteger();

	public int getCount() {
		return count.get();
	}

	public void increment() {
		count.incrementAndGet();
	}

	public static void main(String[] args) {
		Counter counter = new Counter();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
		new MyThread(counter).start();
	}

}

class MyThread extends Thread {
	private Counter counter;

	public MyThread(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			counter.increment();
			@SuppressWarnings("static-access")
			String name = this.currentThread().getName();
			System.out.println(name + "   " + counter.getCount());
	
	}

}