package com.delicacy.oatmeal.common.utils.spring.core.util;

import org.junit.Test;
import org.springframework.util.ObjectUtils;



public class ObjectUtilsTest {
	
	@Test
	public void test(){
		System.out.println(ObjectUtils.getDisplayString(new Object[]{2,"3",true,3.3}));
		//System.out.println(ObjectUtils.addObjectToArray(array, obj));
	}
}
