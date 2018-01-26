/*【程序10】
作者 若水飞天
题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，
求它在 第10次落地时，共经过多少米？第10次反弹多高？ 
 */
package com.delicacy.oatmeal.test.suanfa.classic;


public class TenthTreeFall {
	static double height = 100;
	static double distance = 100;

	public static void main(String[] args) {
		method1();
	}

	/**
	 * 
	 */
	protected static void method1() {
		for (int i = 1; i < 10; i++) {
			distance = distance + height;
			height = height / 2;
		}

		System.out.println("路程：" + distance);
		System.out.println("高度：" + height / 2);
		
		method2(100);
	}

	static int i = 10;
	static double s = 0;
	protected static void method2(double t) {
		s+=t;
		t= t/2;
		s+=t;
		if (--i == 0) {
			System.out.println(s-t);
			System.out.println(t);
			return;
		}
		method2(t);
	}
}