package com.delicacy.oatmeal.test.example;

public final class Employee {

	public String name = null;

	public Employee(String n) {
		this.name = n;
	}
	
	
	// 将两个Employee对象交换
	public static void swap(Employee e1, Employee e2,int i, String s) {
		Employee temp = e1;
		temp.name = "121";
		++i;
		e1 = e2;
		e2 = temp;
		s.substring(1);
		s.replace("a", "b");
		System.out.println(s);
		System.out.println(i);
		System.out.println(e1.name + " " + e2.name); // 打印结果：李四 张三
	}
	
	// 主函数
	public static void main(String[] args) {
		Employee worker = new Employee("张三");
		Employee manager = new Employee("李四");
		System.out.println(worker.hashCode());
		int i = 0;
		String s = "abc";
		//swap里面的worker是对象引用地址副本，引用传递
		//这两个引用地址一样，指向同一个对象
		//i是基本类型，存放在栈中
		//swap里面i是 值传递，方法里面的i改变，不影响方法外面的
		swap(worker, manager,i,s);
		System.out.println(i);
		System.out.println(worker.name + " " + manager.name); // 打印结果仍然是： 张三 李四
	}
}
