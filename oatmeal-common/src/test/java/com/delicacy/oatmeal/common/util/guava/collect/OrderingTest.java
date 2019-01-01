package com.delicacy.oatmeal.common.util.guava.collect;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;


public class OrderingTest  {
	
	@Test
	public void test(){
		Integer nullInt = (Integer) null;
	    Ordering<Iterable<Integer>> example =
	        Ordering.<Integer>natural().nullsFirst().reverse().lexicographical().reverse().nullsLast();
	    List<Integer> list1 = Lists.newArrayList();
	    List<Integer> list2 = Lists.newArrayList(1);
	    List<Integer> list3 = Lists.newArrayList(1, 1);
	    List<Integer> list4 = Lists.newArrayList(1, 2);
	    List<Integer> list5 = Lists.newArrayList(1, null, 2);
	    List<Integer> list6 = Lists.newArrayList(2);
	    List<Integer> list7 = Lists.newArrayList(nullInt);
	    List<Integer> list8 = Lists.newArrayList(nullInt, nullInt);
	    @SuppressWarnings("unchecked")
		List<List<Integer>> list =
	        Lists.newArrayList(list1, list2, list3, list4, list5, list6, list7, list8, null);
	    List<List<Integer>> sorted = example.sortedCopy(list);
	    System.out.println(sorted);
	    // [[null, null], [null], [1, null, 2], [1, 1], [1, 2], [1], [2], [], null]
	}
}
