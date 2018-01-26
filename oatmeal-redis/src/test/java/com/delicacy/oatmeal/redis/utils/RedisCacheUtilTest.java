package com.delicacy.oatmeal.redis.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {RedisServiceApplication.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheUtilTest {

	@Test
	public void test() {
		String ss = RedisCacheUtil.get("helloworld");
		if (ss == null) {
			RedisCacheUtil.set("helloworld", "helloworld");
			ss = RedisCacheUtil.get("helloworld");
			System.out.println(ss);

		}
	}

}
