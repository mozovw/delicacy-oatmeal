/*【程序20】 
 * 作者 若水飞天
题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。 
1.程序分析：请抓住分子与分母的变化规律。
 */

package com.delicacy.oatmeal.test.suanfa.classic;

import java.text.DecimalFormat;

public class TwentiethFractionSum {
	static double sum = 0.0;

	public static void main(String[] args) {
		method1();

		method2(2, 1, 20);
		System.out.println(sum);
		Integer i = new Integer(1);
		method3(i);
		System.out.println(i += 1);
		System.out.println(i);
	}

	protected static Integer method3(Integer up) {
		up = new Integer(10 + up);
		return up;
	}

	protected static void method2(int up, int down, int count) {
		Double d = (double) up / down;
		sum += d;
		int s = up + down;
		down = up;
		up = s;
		if (--count == 0) {
			return;
		}
		method2(up, down, count);
	}

	/**
	 * 
	 */
	protected static void method1() {
		int x = 2, y = 1, t;
		double sum = 0;

		DecimalFormat df = new DecimalFormat("#0.0000");

		for (int i = 1; i <= 20; i++) {
			sum += (double) x / y;
			t = y;
			y = x;
			x = y + t;
			System.out.println("第 " + i + " 次相加，和是 " + df.format(sum));
		}
	}

}