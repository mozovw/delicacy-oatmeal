package com.delicacy.oatmeal.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheUtilTest {

	@Test
	public void test() {

	}

	private void todoSomething() {
		String e = "helloworld";
		RedisCacheUtil.set(e, e);
		String ss = RedisCacheUtil.get(e);
		System.out.println(ss);
		RedisCacheUtil.del(e);
	}


	@Autowired
	private StringRedisTemplate redisTemplate;

	static ConcurrentHashMap<String, String> mapLock = new ConcurrentHashMap<>();

	/**
	 * 缓存雪崩的方案
	 * 查询车票余额并发量
	 * @param key
	 * @param time
	 * @param function 查询数据库的数据
	 * @param supplier 降级后的数据处理
	 * @return
	 */
	public  Object getValue(String key, int time, Function<String,String> function, Supplier<String> supplier) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String value = valueOperations.get(key);
		if (value != null) {
			log.info("{}从缓存中取到数据:{}", Thread.currentThread().getName(), value);
			return value;
		}
		boolean lock = false;
		try {
			// 如果有数据不会被替换掉
			lock = mapLock.putIfAbsent(key, "lock") == null;
			if (lock) {
				//todo 从数据库中查询数据
				value = function.apply(key);
				log.info("{}数据库中取到数据:{}", Thread.currentThread().getName(), value);
				valueOperations.set(key, value, time);
				//todo 将数据放入备份缓存
				//todo 返回数据
				return value;
			} else {
				//todo 从备份缓存获取数据
				//todo 执行降级策略
				if (value == null) {
					log.info("{}执行降级策略,数据:{}", Thread.currentThread().getName(), value);
					return supplier.get();
				}
			}
		} finally {
			if (lock) {
				mapLock.remove(key);
			}
		}
		return null;
	}

}
