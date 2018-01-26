/*【程序36】 
 * 作者    若水飞天
题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数 
 **/
package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

public class Thirty_sixthBackShift {
	public static final int N = 10;

	public static void main(String[] args) {
		int[] a = new int[N];
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("please input array a, ten numbers:");
		for (int i = 0; i < a.length; i++) {
			a[i] = s.nextInt();
		}
		System.out.println("please input m , one number:");
		int m = s.nextInt();

		int[] b = new int[m];
		int[] c = new int[N - m];
		for (int i = 0; i < m; i++) {
			b[i] = a[i];
		}

		for (int i = m, j = 0; i < N; i++, j++) {
			c[j] = a[i];
		}

		for (int i = 0; i < N - m; i++) {
			a[i] = c[i];
		}

		for (int i = m, j = 0; i < N; i++, j++) {
			a[i] = b[j];
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

}