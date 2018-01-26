package com.delicacy.oatmeal.test.example;


public class MethodObject {
	static MethodObject m  = new MethodObject();
	public int id ;
	public static void main(String[] args) {
		MethodObject mm  = new MethodObject();
		mm.change(mm);
		System.out.println(mm.id);
		m.change(m);
		System.out.println(m.id);
	}
	public void change(MethodObject mm){
		++ mm.id;
	}
}
