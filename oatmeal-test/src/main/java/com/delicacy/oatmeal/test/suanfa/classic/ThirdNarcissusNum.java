/*【程序3】
作者 若水飞天
题目：打印出所有的"水仙花数(narcissus number)"，所谓"水仙花数"是指一个三位数，
其各位数字立方和等于该数本身。例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。 
1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。 */
package com.delicacy.oatmeal.test.suanfa.classic;

public class ThirdNarcissusNum {

	public static void main(String[] args) {

		method();

	}


	public static void method() {
		for (int m = 100; m <= 10000; m++) {
			int a = m/100,aa = m%100/10,aaa = m%100%10;
			if (a*a*a+aa*aa*aa+aaa*aaa*aaa==m)System.out.println(m);
		}
	}
}