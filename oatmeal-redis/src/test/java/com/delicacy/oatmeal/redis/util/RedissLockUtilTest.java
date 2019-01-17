package com.delicacy.oatmeal.redis.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissLockUtilTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() {
        String helloworld = "helloworld";
        ConcurrentTestUtil.test(helloworld, e -> {
            ResponseEntity<String> forEntity = restTemplate
                    .getForEntity("http://localhost:8080/sendMessage?message="+Thread.currentThread().getName(), String.class);
            String body = forEntity.getBody();
            System.out.println(body);
        }, 2);
    }

    private void doRedisson() {
        RLock redLock = redissonClient.getLock("REDLOCK_KEY");
        boolean islock;
        try {
            islock = redLock.tryLock(5, 10, TimeUnit.SECONDS);
            if (islock) {
                System.out.println(redissonClient.isShutdown());
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(redissonClient.isShutdown());
            redLock.unlock();
        }
    }


    private void doTryLock(String e) {
        // System.out.println(Thread.currentThread().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        boolean tryLock = RedissLockUtil.tryLock(e, 10, 10);
        if (tryLock)
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                RedissLockUtil.unlock(e);
            }
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println(totalTimeSeconds);
    }

    private void doLock(String e) {
        System.out.println(Thread.currentThread().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        RedissLockUtil.lock(e, 3);
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName());
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // 两个线程同key，一个正常走，另一个就InterruptedException，接着走finally内容
            // 那么正常的线程在走finally内容就发现key没有了，就报错
            // 加个等待时间
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            RedissLockUtil.unlock(e);
        }
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println(totalTimeSeconds);
    }
}
