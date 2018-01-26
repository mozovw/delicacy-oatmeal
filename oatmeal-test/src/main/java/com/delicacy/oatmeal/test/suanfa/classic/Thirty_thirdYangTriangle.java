package com.delicacy.oatmeal.test.suanfa.classic;
public class Thirty_thirdYangTriangle {
	/* 作者   若水飞天
	题目：打印出杨辉三角形（要求打印出10行如下图） 
	1.程序分析： 
	        1 
	       1 1 
	      1 2 1 
	     1 3 3 1 
	    1 4 6 4 1 
	   1 5 10 10 5 1
	*/
	/*
	* 网上千篇一律是这种写法，我也没有什么创新，
	* a[i][j]=a[i-1][j]+a[i-1][j-1] 就是这个程序的核心
	* 定义的是二维数组，为了使输出的结果看起来漂亮一点
	* 可以用for（int k=0; k<2*(10-i)-1; k++）控制输出的空格
	* 这个循环是在控制行数的循环里面，控制列数的循环外面。
	* 记得在输出菱形时为了控制下半部分的输出，在下拼命的写出
	* for(int k=1; k<=WIDTH-2*i-1; k++) 才算了事。
	*/
	
	public static void main(String[] args) {

		int[][] a = new int[10][10];
		for (int i = 0; i < 10; i++) {
			a[i][i] = 1;
			a[i][0] = 1;
		}
		for (int i = 2; i < 10; i++) {
			for (int j = 1; j < i; j++) {
				a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
				//System.out.println(a[i][j]);
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 2 * (10 - i) - 1; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.print(a[i][j] + "   ");
			}
			System.out.println();
		}
	}
}