package com.holmes.zookeeper.clients.curator.locks;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterProcessReadWriteLockDemo {

    private final static Logger log = LoggerFactory.getLogger(InterProcessReadWriteLockDemo.class);

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        executorService.submit(() -> {
            testWriteLock();
        });
        TimeUnit.MILLISECONDS.sleep(500);
        executorService.submit(() -> {
            testReadLock();
        });
        executorService.submit(() -> {
            testReadLock();
        });
        executorService.submit(() -> {
            testReadLock();
        });
        TimeUnit.MILLISECONDS.sleep(500);
        executorService.submit(() -> {
            testWriteLock();
        });

        executorService.shutdown();
    }

    private static void testReadLock() {

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(CuratorFrameworkCreate.getCuratorFramework(),
                "/read_write_lock");
        try {
            // 获取读锁
            InterProcessMutex readLock = readWriteLock.readLock();
            readLock.acquire();
            log.info(Thread.currentThread().getName() + " 获取到读锁");
            TimeUnit.SECONDS.sleep(5);
            readLock.release();
            log.info(Thread.currentThread().getName() + " 释放了读锁");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testWriteLock() {

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(CuratorFrameworkCreate.getCuratorFramework(),
                "/read_write_lock");
        try {
            // 获取写锁
            InterProcessMutex writeLock = readWriteLock.writeLock();
            writeLock.acquire();
            log.info(Thread.currentThread().getName() + " 获取到写锁");
            TimeUnit.SECONDS.sleep(3);
            writeLock.release();
            log.info(Thread.currentThread().getName() + " 释放了写锁");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
