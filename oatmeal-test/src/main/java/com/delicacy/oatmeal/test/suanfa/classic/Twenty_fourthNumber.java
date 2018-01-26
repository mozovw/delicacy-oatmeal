package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

public class Twenty_fourthNumber {
	public static void main(String[] args) {

		Twenty_fourthNumber tn = new Twenty_fourthNumber();
		Scanner s = new Scanner(System.in);
		long a = s.nextLong();
		String ss = String.valueOf(a);
		char[] ch = ss.toCharArray();
		System.out.println(a + "是" + ch.length + "位数");
		for (int i = ch.length - 1; i >= 0; i--) {
			System.out.print(ch[i]);
		}
	}
}