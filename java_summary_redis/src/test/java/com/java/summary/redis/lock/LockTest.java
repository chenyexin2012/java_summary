package com.java.summary.redis.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class LockTest {

    private int value = 0;

    private void addValue() {

        Lock lock = null;
        try {
            lock = new RedisLock("add_value");
            lock.lock();
            value++;
        } catch (RedisLockException e) {
            e.printStackTrace();
        } finally {
            if(null != lock) {
                lock.unlock();
            }
        }
    }

    @Test
    public void test() {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        addValue();
                    }
                }
            });
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }

        System.out.println(value);
    }
}
