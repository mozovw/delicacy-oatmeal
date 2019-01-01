package com.delicacy.oatmeal.common.util.guava.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.google.common.base.Strings;


public class StringsTest  {
	
	@Test
	public void test(){
		assertEquals("", Strings.nullToEmpty(null));
		assertNull(Strings.emptyToNull(""));
	}
}
