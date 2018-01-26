package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

public class Twenty_fifthPalindrom {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("请输入一个正整数：");
		long a = s.nextLong();
		String ss = Long.toString(a);
		char[] ch = ss.toCharArray();
		boolean is = true;
		int j = ch.length;
		for (int i = 0; i < j / 2; i++) {
			if (ch[i] != ch[j - i - 1]) {
				is = false;
			}
		}
		if (is == true) {
			System.out.println("这是一个回文数");
		} else {
			System.out.println("这不是一个回文数");
		}
		method1();
	}
	
	private static void method1(){
		for (int i = 10000; i <= 99999; i++) {
			String value = String.valueOf(i);
			char[] c = value.toCharArray();
			if (c[0]==c[4] && c[1]==c[3]) {
				System.out.println(String.valueOf(c));
			}
		}
	}
}
