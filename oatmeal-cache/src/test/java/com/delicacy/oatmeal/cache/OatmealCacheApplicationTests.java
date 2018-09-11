package com.delicacy.oatmeal.cache;

import com.delicacy.oatmeal.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OatmealCacheApplicationTests {

	@Autowired
	private CacheService cacheService;

	@Test
	public void contextLoads() {
		String uuid = cacheService.uuid("0911");
		System.out.println(uuid);
	}

}
