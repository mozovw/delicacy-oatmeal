/*【程序28】 
 * 作者 若水飞天
题目：对10个数进行排序 
1.程序分析：可以利用选择法，即从后9个比较过程中，
选择一个最小的与第一个元素交换， 下次类推，
即用第二个元素与后8个进行比较，并进行交换。
 **/
package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

public class Twehty_eighthNumberSort {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = s.nextInt();
		}
		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < 8
					; j++) {
				if (a[i] > a[j]) {
					int t = a[i];
					a[i] = a[j];
					a[j] = t;
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			System.out.print(a[i] + " ");
		}

	}

}