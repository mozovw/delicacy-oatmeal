package com.delicacy.oatmeal.test.example;


public class StaticOrder {

	public static void main(String[] args) {
		StaticOrder.print();
		new StaticOrder().print2();
		System.out.println(!new StaticOrder().tryAcquire(0));
		
	}

	protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
	
	static {
		System.out.println(1);
	}
	{
		System.out.println(3);
	}

	static void print() {
		System.out.println(2);
	}

	private void print2() {
		System.out.println(4);
	}
}
