/*【程序6】
作者 若水飞天
题目：输入两个正整数m和n，求其最大公约数和最小公倍数。 
1.程序分析：利用辗除法。 */
package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

public class SixthCommonDiviser {
	public static void main(String[] args) {
		int a, b;
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		a = s1.nextInt();
		b = s2.nextInt();
		SixthCommonDiviser scd = new SixthCommonDiviser();
		int m = scd.division(a, b);
		int n = a * b / m;
		System.out.println("最大公约数: " + m);
		System.out.println("最小公倍数: " + n);
	}

	public int division(int x, int y) {
		int t;
		if (x < y) {
			t = x;
			x = y;
			y = t;
		}

		while (y != 0) {
			if (x == y)
				return 1;
			else {
				int k = x % y;
				x = y;
				y = k;
			}
		}
		return x;
	}
}