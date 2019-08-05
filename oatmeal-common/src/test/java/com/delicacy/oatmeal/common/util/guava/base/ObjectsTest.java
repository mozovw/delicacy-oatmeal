package com.delicacy.oatmeal.common.util.guava.base;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.base.Objects;


public class ObjectsTest  {

	@Test
	public void test() {
		Objects.equal("a", "a"); // returns true
		Objects.equal(null, "a"); // returns false
		Objects.equal("a", null); // returns false
		Objects.equal(null, null); // returns true

		// exam distinct string objects
		String s1 = "foobar";
		String s2 = new String(s1);
		assertTrue(Objects.equal(s1, s2));
		
	}
}
