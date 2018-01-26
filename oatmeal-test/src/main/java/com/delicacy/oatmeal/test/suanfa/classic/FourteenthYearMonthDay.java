package com.delicacy.oatmeal.test.suanfa.classic;

import java.util.Scanner;

/*【程序14】
 * 作者 若水飞天 
 题目：输入某年某月某日，判断这一天是这一年的第几天？ 
 1.程序分析：以3月5日为例，应该先把前两个月的加起来，
 然后再加上5天即本年的第几天，特殊情况，闰年且输入月份大于3时需考虑多加一天。 
 */
public class FourteenthYearMonthDay {
	public static void main(String[] args) {
		int year, month, day;
		int days = 0;
		int d = 0;

		FourteenthYearMonthDay fymd = new FourteenthYearMonthDay();

		System.out.print("Input the year:");
		year = fymd.input();
		System.out.print("Input the month:");
		month = fymd.input();
		System.out.print("Input The Day:");
		day = fymd.input();

		if (year < 0 || month < 0 || month > 12 || day < 0 || day > 31) {
			System.out.println("Input error, please run this program again!");
			System.exit(0);
		}
		for (int i = 1; i < month; i++) {
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				// d += days;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				// d += days;
				break;
			case 2:
				if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
					days = 29;
				} else {
					days = 28;
				}
				// d += days;
				break;
			}

			d += days;

		}
		System.out.println(year + "-" + month + "-" + day + "是今年的第" + (d + day)
				+ "天。");
	}

	public int input() {
		int value = 0;
		Scanner s = new Scanner(System.in);
		value = s.nextInt();
		return value;
	}
}