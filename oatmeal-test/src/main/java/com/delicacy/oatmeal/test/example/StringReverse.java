package com.delicacy.oatmeal.test.example;

public class StringReverse {
	public static void main(String[] args) {
		String string = "abcdefg";
		int i = 3;
		int n = string.length();
//		string.reverse(0, i - 1); // cbadefgh
//		string.reverse(i, n - 1); // cbahgfed
//		string.reverse(0, n - 1); // defghabc
	}
}

class StrExt {
	static String string = "";

	public void reverse(String str, int offset, int limit) {
		int length = str.length();
		if (offset + limit <= length & offset < limit) {
			str.substring(offset, offset+limit);
		}
	}
}
