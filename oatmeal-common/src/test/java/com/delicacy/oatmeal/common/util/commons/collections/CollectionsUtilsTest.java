package com.delicacy.oatmeal.common.util.commons.collections;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

public class CollectionsUtilsTest{
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void test(){
		List<User> list = new ArrayList<User>();
		list.add(new User("a"));
		list.add(new User("b"));
		list.add(new User("c"));
		list.add(new User("c"));
		//是否包含
		System.out.println(list.contains(new User("b")));
		List<User> list2 = new ArrayList<User>();
		list2.add(new User("d"));
		list2.add(new User("b"));
		System.out.println(CollectionUtils.isEmpty(new ArrayList<String>()));
		//包含多少个	
		System.out.println(CollectionUtils.countMatches(list, new User("c")));
		//是否存在
		System.out.println(CollectionUtils.exists(list, new User("c")));
		//并集
		System.out.println(CollectionUtils.union(list, list2));
		//差集
		System.out.println(CollectionUtils.subtract(list, list2));
	}
}


