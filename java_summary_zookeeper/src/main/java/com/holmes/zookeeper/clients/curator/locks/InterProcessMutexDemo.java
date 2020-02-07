package com.holmes.zookeeper.clients.curator.locks;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import com.holmes.zookeeper.clients.curator.Increase;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class InterProcessMutexDemo {

    private final static Logger log = LoggerFactory.getLogger(InterProcessMutexDemo.class);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                // 可重入排它锁
                InterProcessMutex lock = new InterProcessMutex(CuratorFrameworkCreate.getCuratorFramework(), "/increase");
                for (int j = 0; j < 10; j++) {
                    try {
                        lock.acquire();
//                        lock.acquire();
                        log.info(Thread.currentThread().getName() + " 获取到锁");
                        Increase.increase();
                        lock.release();
//                        lock.release();
                        log.info(Thread.currentThread().getName() + " 释放了锁");
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
        log.info("time = {}, value = {}", end - start, Increase.num);
    }
}
