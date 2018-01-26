package com.delicacy.oatmeal.test.exam.string;

public class StringConstantDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
		method5();
		method6();
	}// true

	/**
	 * 当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（用 equals(Object)
	 * 方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并返回此 String 对象的引用。 它遵循以下规则：对于任意两个字符串
	 * s 和 t，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true。
	 */
	protected static void method1() {
		String a = "a1";
		String b = "a" + 1;
		System.out.println(a == b);
	}
	protected static void method2() {
		String a = "ab";
		String bb = "b";
		String b = "a" + bb; // 编译器不能确定为常量
		System.out.println(a == b);
	}

	protected static void method3() {
		String a = "ab";
		final String bb = "b";
		String b = "a" + bb; // bb加final后是常量，可以在编译器确定b
		System.out.println(a == b);
	}

	protected static void method4() {
		// String a = "ab" ab是在编译期间放到常量池中
		String a = "ab";
		final String bb = getBB();
		String b = "a" + bb;// bb是通过函数返回的，虽然知道它是final的，但不知道具体是啥，要到运行期才知道bb的值
		System.out.println(a == b);
	}

	private static String getBB() {
		return "b";
	}

	private static String a = "ab";

	protected static void method5() {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;// +的用法
		System.out.println(s == a);
		System.out.println(s.intern() == a);// intern的含义
	}

	protected static void method6() {

	}

}