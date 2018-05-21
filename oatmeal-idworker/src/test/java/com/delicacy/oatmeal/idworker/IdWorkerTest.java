package com.delicacy.oatmeal.idworker;

import com.delicacy.oatmeal.idworker.idworker.IdWorker;
import com.delicacy.oatmeal.idworker.idworker.IdWorkerExt;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class IdWorkerTest {

    HashSet<Long> set = new HashSet<Long>();
    int i = 0 ;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    @Test
    public void contextLoads() throws InterruptedException {
        ConcurrentTestUtil.test(new IdWorker(1), s -> {
            set.add(s.nextId());
            i++;
            atomicInteger.incrementAndGet();
        }, 1000);

        ConcurrentTestUtil.test( s -> {
            atomicInteger.incrementAndGet();
        });

        System.out.println(set.size());
        System.out.println(i);
        System.out.println(atomicInteger.get());
    }

}
