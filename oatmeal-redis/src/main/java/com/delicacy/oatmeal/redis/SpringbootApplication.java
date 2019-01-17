package com.delicacy.oatmeal.redis;

import com.delicacy.oatmeal.redis.util.RedissLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@SpringBootApplication
@ComponentScan("com.delicacy")
public class SpringbootApplication {

	@GetMapping("sendMessage")
	public String sendMessage(String message, @RequestParam(required = false) Integer timeout) {
		return doRedisson(message);
	}

	private String doUtil(String message) {
		String e = "REDLOCK_KEY";
		boolean tryLock = RedissLockUtil.tryLock(e, 2,2);
		if (tryLock)
		//RedissLockUtil.lock(e,2);
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			System.out.println(Thread.currentThread().getName());
			countdown(5);
			stopWatch.stop();
			double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
			System.out.println(totalTimeSeconds);
			return "success";
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			RedissLockUtil.unlock(e);
		}
		return message;
	}

	@Autowired
	private RedissonClient redissonClient;
	private String doRedisson(String message) {
		RLock redLock = redissonClient.getLock("REDLOCK_KEY");
		boolean tryLock = false;
		try {
//			tryLock = redLock.tryLock(2,5, TimeUnit.SECONDS);
//			if (!tryLock) return message;
			redLock.lock(2,TimeUnit.SECONDS);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			System.out.println(Thread.currentThread().getName());
			countdown(5);
			stopWatch.stop();
			double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
			System.out.println(totalTimeSeconds);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			redLock.unlock();
		}
		return message;
	}

	private void countdown(Integer timeout)  {
		Random random = new Random();
		int i = random.nextInt(timeout);
		//int i = timeout;
		while (i != 0) {
			i--;
			log.warn(String.valueOf(i));
			try {
				TimeUnit.SECONDS.sleep((long) 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
