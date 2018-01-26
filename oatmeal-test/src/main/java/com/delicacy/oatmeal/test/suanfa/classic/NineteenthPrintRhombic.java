package com.delicacy.oatmeal.test.suanfa.classic;

public class NineteenthPrintRhombic {
	static final int HEIGHT = 7;
	static final int WIDTH = 8;

	public static void main(String[] args) {
		for (int i = 0; i < (HEIGHT + 1) / 2; i++) {
			for (int j = 1; j < WIDTH / 2 - i; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k < (i + 1) * 2; k++) {
				System.out.print('*');
			}
			System.out.println();
		}

		for (int i = 1; i <= HEIGHT / 2; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= WIDTH - 2 * i - 1; k++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}
}