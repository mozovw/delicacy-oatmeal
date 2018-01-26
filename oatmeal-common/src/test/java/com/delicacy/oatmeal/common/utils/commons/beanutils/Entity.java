package com.delicacy.oatmeal.common.utils.commons.beanutils;
public class Entity {

	private Integer id = 5;
	private String name = "rongxinhua";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String haha() {
		return "Ha,Ha";
	}

	public void sayHelle(String name) {
		System.out.println(name + " say, Hello!");
	}

	public String countAges(int x, int y) {
		return "My Age is " + (x + y);
	}

}