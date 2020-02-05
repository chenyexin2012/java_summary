package com.holmes.zookeeper.distributelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    private final static Logger log = LoggerFactory.getLogger(Test.class);

    private static int num = 0;

    private static void increase() {
        num++;
        num--;
        num++;
        num++;
        num--;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {

                ZookeeperDistributeLock lock = new ZookeeperDistributeLock("127.0.0.1:2181",
                        10000, "lock", 1000L, 1000L);
                for (int j = 0; j < 10000; j++) {
                    lock.lock();
                    log.info(Thread.currentThread().getName() + " 获取到锁");
                    increase();
                    lock.unlock();
                    log.info(Thread.currentThread().getName() + " 释放了锁");

                }
            });
        }

        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }

        long end = System.currentTimeMillis();
        log.info("time = {}, value = {}", end - start, num);
    }
}
