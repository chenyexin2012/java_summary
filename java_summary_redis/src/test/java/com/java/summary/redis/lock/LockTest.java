package com.java.summary.redis.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTest {

    private int value = 0;

    private RedisLock lock;

    {
        try {
            lock = new RedisLock("increment", 100, 0L);
        } catch (RedisLockException e) {
            e.printStackTrace();
        }
    }

    private void addValue() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test() {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
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
