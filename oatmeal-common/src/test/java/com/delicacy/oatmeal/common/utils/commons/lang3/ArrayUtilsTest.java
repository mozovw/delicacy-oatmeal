package com.delicacy.oatmeal.common.utils.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;



public class ArrayUtilsTest  {
	
	@Test
	public void test() {
		/* 1.ArrayUtils.isEmpty(strs):判断数组是否为空, 不为空返回false, 为空true */
		ArrayUtils.isEmpty(new String[] { "21", "是" });// 结果是false
		ArrayUtils.isEmpty(new String[] { "" });// 结果是false
		ArrayUtils.isEmpty(new String[] { null });// 结果是false
		ArrayUtils.isEmpty(new String[] {});// 结果是true

		/* 2.ArrayUtils.isNotEmpty(strs):判断数组是否不为空,不为空返回true,为空false */
		ArrayUtils.isNotEmpty(new String[] { "21", "是" });// 结果是true
		ArrayUtils.isNotEmpty(new String[] { "" });// 结果是true
		ArrayUtils.isNotEmpty(new String[] {});// 结果是false

		/*
		 * 3.ArrayUtils.isSameLength(strs,strs2):判断两个数组长度是否相等,长度相等返回true,否则返回false
		 * 。相比较的两个数组类型必须相同
		 */
		ArrayUtils.isSameLength(new String[] { "21", "是" }, new String[] {
				"21", "是" });// 返回false

		/* 4.ArrayUtils.isSameType(strs,strs2):判断两个数组的类型是否相同,相同返回true,否则返回false */
		ArrayUtils.isSameType(new String[] { "21", "是" }, new Integer[] { 3 });


		/* 6.ArrayUtils.toString()将一个数组转换成String,用于打印 */
		ArrayUtils.toString(new String[] { "21", "是" });// 结果是：{21,是}

		/* 7.ArrayUtils.clone赋值（克隆）数组 */
		Object[] s = ArrayUtils.clone(new Object[] { "33", "yy" });
		/*
		 * 8.ArrayUtils.subarray截取子数组：
		 * 根据起始索引startIndexInclusive到结束索引startIndexInclusive
		 */
		Object[] s1 = ArrayUtils.subarray(new Object[] { "33", "yy", "uu" }, 0,
				1);// 结果是返回数组：[33]
		Object[] s2 = ArrayUtils.subarray(new Object[] { "33", "yy", "uu" }, 0,
				2);// 结果是返回数组：[33,yy]
		
		/* 9.ArrayUtils.indexOf查询某个object在数组中的位置，可是指定起始搜索位置 */
		ArrayUtils.indexOf(new Object[] { "33", "yy", "uu" }, "uu");// 结果是2
		ArrayUtils.indexOf(new Object[] { "33", "yy", "uu" }, "uu", 2);// 结果是2
		ArrayUtils.indexOf(new Object[] { "33", "yy", "uu" }, "uu", 3);// 结果是-1

		/* 10.ArrayUtils.lastIndexOf反向查询某个object在数组中的位置，可以指定起始搜索位置 */
		ArrayUtils.lastIndexOf(new Object[] { "33", "yy", "uu" }, "33");// 结果是0
		ArrayUtils.lastIndexOf(new Object[] { "33", "yy", "uu" }, "33", 2);

		/* 11.ArrayUtils.contains查询某个object是否在数组中 */
		ArrayUtils.contains(new String[] { "1", "2", "3" }, "11");

		/* 12.ArrayUtils.reverse反转数组 */
		ArrayUtils.reverse(new String[] { "22", "yy" });// 结果是：{"yy"，"22"}

		/* 13.ArrayUtils.add添加一object到数组 */
		String[] t = { "22", "yy" };
		String[] gg = (String[]) ArrayUtils.add(t, "jj");// {"22","yy","jj"}
		/* 14.ArrayUtils.addAll合并两个数组 */
		String[] ggo = (String[]) ArrayUtils.addAll(
				new String[] { "22", "yy" }, new String[] { "jj" });// 结果是：[22,yy,jj]
		ArrayUtils.addAll(new String[] { "22", "yy" }, new String[] { "jj",
				"jj" }); // 结果是：[22,yy,jj,jj]

		/* 15.ArrayUtils.remove删除数组某个位置的元素 */
		String[] gg4 = (String[]) ArrayUtils.remove(
				new String[] { "22", "yy" }, 1);
		/* 16.ArrayUtils.removeElement删除数组中某个对象 */
		String[] ggpp = (String[]) ArrayUtils.removeElement(new String[] {
				"22", "yy" }, "yy");
	}

}
