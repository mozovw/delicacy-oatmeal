package com.delicacy.oatmeal.test.suanfa.classic;

/*【程序9】 
 题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。
 例如6=1＋2＋3.编程 找出1000以内的所有完数。 
 */
public class NinthWanshu {

	public static void main(String[] args) {

		System.out.println("1到1000的完数有： ");
		for (int i = 1; i < 1000; i++) {
			int t = 0;
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					t = t + j;
				}
			}
			if (t == i) {
				System.out.print(i + " ");
			}
		}
	}
}