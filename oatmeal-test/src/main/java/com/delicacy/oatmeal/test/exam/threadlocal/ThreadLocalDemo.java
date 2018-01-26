package com.delicacy.oatmeal.test.exam.threadlocal;
//
public class ThreadLocalDemo {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "hello";
		}
	};
	//ConcurrentHashMap
	static class MyRunnable implements Runnable {
		private int num;

		public MyRunnable(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			threadLocal.set(String.valueOf(++num));
			System.out.println(Thread.currentThread().getName() + "  "
					+ "threadLocalValue:" + threadLocal.get());
		}
	}

	public static void main(String[] args) {
		System.out.println(threadLocal.get());
		new Thread(new MyRunnable(1)).start();
		new Thread(new MyRunnable(2)).start();
		new Thread(new MyRunnable(3)).start();
	}
}