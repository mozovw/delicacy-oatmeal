/*【程序37】 
 * 作者 若水飞天
题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），
凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 **/
package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Arrays;
import java.util.Scanner;

public class Thirty_sevenCount3Quit {
	@SuppressWarnings("resource")
	static int n = 8;
	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		int n = s.nextInt();
		method(n);
	}

//	private static void method(int n) {
//		boolean[] arr = new boolean[n];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = true;// 下标为TRUE时说明还在圈里
//		}
//		int leftCount = n;
//		int countNum = 0;
//		int index = 0;
//
//		while (leftCount > 1) {
//			if (arr[index] == true) {// 当在圈里时
//				countNum++; // 报数递加
//				if (countNum == 3) {// 报道3时
//					countNum = 0;// 从零开始继续报数
//					arr[index] = false;// 此人退出圈子
//					leftCount--;// 剩余人数减一
//					System.out.println(Arrays.toString(arr));
//				}
//			}
//			index++;// 每报一次数，下标加一
//
//			if (index == n) {// 是循环数数，当下标大于n时，说明已经数了一圈，
//				index = 0;// 将下标设为零重新开始。
//			}
//		}
//
//		for (int i = 0; i < n; i++) {
//			if (arr[i] == true) {
//				System.out.println(i);
//			}
//		}
//	}


//	private static void method(int n) {
//		boolean[] arr = new boolean[n];
//		int count = 0,remainCount = n,index = 0;
//		while (remainCount>1){
//			if (!arr[index]&&++count==3){
//				remainCount--;
//				count=0;
//				arr[index]=true;
//				System.out.println(Arrays.toString(arr));
//			}
//			if (++index == n)index = 0;
//		}
//		for (int i = 0; i < n; i++) {
//			if (!arr[i]) System.out.println(i);
//		}
//	}

	private static void method(int n) {
		boolean arr[] = new boolean[n];
		int count = 0,index = 0,remainCount = n;
		while (remainCount>1){
			if (++count==3&&!arr[index]){
				count = 0;
				remainCount--;
				arr[index] = true;
				System.out.println(Arrays.toString(arr));
			}
			if (++index == n)index=0;
		}
		for (int j = 0; j < arr.length; j++) {
			if(!arr[j])System.out.println(j);
		}
	}
}