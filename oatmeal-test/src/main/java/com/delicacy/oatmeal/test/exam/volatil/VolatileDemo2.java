package com.delicacy.oatmeal.test.exam.volatil;

public class VolatileDemo2 {
	private  static int i = 0;

	public static void main(String[] args) {
		new SubThread().start();
		new SubThread2().start();
		new SubThread3().start();

	}

	private static class SubThread extends Thread {
		public void run() {
			while (i < 10000) {
				System.out.println(""+i);
				i++;
			}
		}
	}
	//AtomicLong
	private static class SubThread2 extends Thread {
		public void run() {
			while (i < 10000) {
				System.out.println(""+i);
				i++;
			}
		}
	}
	private static class SubThread3 extends Thread {
		public void run() {
			while (i < 10000) {
				System.out.println(""+i);
				i++;
			}
		}
	}
}