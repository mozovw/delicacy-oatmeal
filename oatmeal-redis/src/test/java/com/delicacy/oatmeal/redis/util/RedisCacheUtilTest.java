package com.delicacy.oatmeal.redis.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheUtilTest {

	@Test
	public void test() {
		String e = "helloworld";
		RedisCacheUtil.set(e, e);
		String ss = RedisCacheUtil.get(e);
		System.out.println(ss);
		RedisCacheUtil.del(e);
	}

}
