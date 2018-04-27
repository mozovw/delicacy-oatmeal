package com.delicacy.oatmeal.idworker;

import com.delicacy.oatmeal.idworker.idworker.IdWorker;
import com.delicacy.oatmeal.idworker.idworker.IdWorkerExt;
import org.junit.Test;


public class IdWorkerTest {

    @Test
    public void contextLoads() {
        ConcurrentTestUtil.test(new IdWorker(1), s -> {
            System.out.println(s.nextId());
        }, 10000);
    }

}
