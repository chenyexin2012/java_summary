package com.holmes.zookeeper.clients.curator.atomic;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedAtomicIntegerDemo {

    private final static Logger log = LoggerFactory.getLogger(DistributedAtomicIntegerDemo.class);

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {

        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(
                CuratorFrameworkCreate.getCuratorFramework(), "/atomicInt",
                new RetryNTimes(10, 100));
        distributedAtomicInteger.trySet(0);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 8; i++) {
            executorService.submit(() -> {
                DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(
                        CuratorFrameworkCreate.getCuratorFramework(), "/atomicInt",
                        new RetryNTimes(10, 100));
                for (int j = 0; j < 100; j++) {
                    try {
                        atomicInteger.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(
                        CuratorFrameworkCreate.getCuratorFramework(), "/atomicInt",
                        new RetryNTimes(10, 100));
                for (int j = 0; j < 100; j++) {
                    try {
                        atomicInteger.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        }

        long end = System.currentTimeMillis();
        try {
            log.info("time = {}, value = {}", end - start, distributedAtomicInteger.get().preValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
