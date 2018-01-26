package com.delicacy.oatmeal.test.exam.volatil;
//java volatile不能保证原子性
public class VolatileDemo {

	private volatile int count = 0;

	public  int getCount() {
		return this.count;
	}

	public  void setCount() {
		
		this.count++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final VolatileDemo demo = new VolatileDemo();
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.setCount();
				}
			}).start();
		}
		while (Thread.activeCount() > 1) {
			Thread.yield();
			//ThreadGroup
		}
		System.out.println(demo.getCount());
	}

}