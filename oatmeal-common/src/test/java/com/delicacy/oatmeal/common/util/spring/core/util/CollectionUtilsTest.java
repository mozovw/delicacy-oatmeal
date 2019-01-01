package com.delicacy.oatmeal.common.util.spring.core.util;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CollectionUtilsTest  {
	@Test
	public void test() {
		log.error("");
		List<String> list = new ArrayList<String>();
		String b = new String("b");
		list.add(new String("a"));
		list.add(b);
		list.add(new String("c"));
		list.add(new String("c"));
		System.out.println(list.contains(new String("b")));
		List<String> list2 = new ArrayList<String>();
		list2.add(new String("d"));
		list2.add(new String("b"));
		System.out.println(CollectionUtils.containsInstance(list, b));
		System.out.println(CollectionUtils.isEmpty(list));
		// Generally prefer the standard Arrays.asList method. This arrayToList
		// method is just meant to deal with an incoming Object value that might
		// be an Object[] or a primitive array at runtime.
		System.out.println(CollectionUtils
				.arrayToList(new String[] { "a", "b" }));
		System.out.println(Arrays.asList(new String[] { "a", "b" }));
		Properties props = new Properties();
		props.setProperty("1", "a");
		Map<String, String> map = new HashMap<String, String>();
		CollectionUtils.mergePropertiesIntoMap(props, map);
		System.out.println(map);
		CollectionUtils.mergeArrayIntoCollection(new String[] { "a", "b" }, list);
		System.out.println(list);
	}
}
