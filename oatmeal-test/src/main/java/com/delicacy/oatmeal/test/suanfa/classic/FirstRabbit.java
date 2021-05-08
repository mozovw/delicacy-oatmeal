/*【程序1】
题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
1.程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21.... 
 */
package com.delicacy.oatmeal.test.suanfa.classic;

public class FirstRabbit {
	public static final int MONTH = 15;

	public static void main(String[] args) {
		method1();
		System.out.println("====================");
		method2(1, 1, 3, MONTH);
	}

//	protected static void method2(int f1, int f2, int month1, int month2) {
//		int f = f2;
//		f2 = f1 + f2;
//		f1 = f;
//		System.out.println("第" + month1 + "个月的兔子对数: " + f2);
//		if (month1 >= month2) return;
//		method2(f1, f2, ++month1, month2);
//	}

	protected static void method2(int f1, int f2, int month1, int month2) {
		if(month1>month2)return;
		int tmp = f2;
		f2 = f1+f2;
		f1 = tmp;
		System.out.println("第" + month1 + "个月的兔子对数: " + f2);
		method2(f1,f2,++month1,month2);
	}

//	protected static void method1() {
//		int f1 = 1, f2 = 1,f;
//		for (int i = 3; i <= MONTH; i++) {
//			f = f2;
//			f2 = f1 + f2;
//			f1 = f;
//			System.out.println("第" + i + "个月的兔子对数: " + f2);
//		}
//	}

	protected static void method1() {
		int f1 = 1,f2 =1,tmp,i;
		for (i = 3; i <= 15; i++) {
			tmp = f2;
			f2 = f2 +f1;
			f1 = tmp;
			System.out.println("第" + i + "个月的兔子对数: " + f2);
		}

	}
}