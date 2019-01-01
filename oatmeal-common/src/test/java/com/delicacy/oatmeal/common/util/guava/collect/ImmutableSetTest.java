package com.delicacy.oatmeal.common.util.guava.collect;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;


/**
 * 当对象被不可信的库调用时，不可变形式是安全的； 不可变对象被多个线程调用时，
 * 不存在竞态条件问题 不可变集合不需要考虑变化，因此可以节省时间和空间。
 * 所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 *  不可变对象因为有固定不变，可以作为常量来安全使用。
 * 
 * @ClassName: ImmutableSetTest
 * @Description: TODO
 * @author Frank
 */
public class ImmutableSetTest  {

	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red", "orange", "yellow", "green", "blue", "purple");
	
	public static final ImmutableSet<String> NAMES = ImmutableSet.copyOf(Sets.newHashSet("a","b"));
	
	@Test
	public void test() {
		ImmutableSet<String> set = ImmutableSet.of(
		        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "a");
		UnmodifiableIterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
}
