package com.holmes.zookeeper.clients.curator.barrier;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import com.holmes.zookeeper.clients.curator.locks.InterProcessMutexDemo;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单屏障
 */
public class DistributedBarrierDemo {

    private final static Logger log = LoggerFactory.getLogger(InterProcessMutexDemo.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for(int i = 0; i < 5; i++) {

            executorService.submit(() -> {

                DistributedBarrier distributedBarrier = new DistributedBarrier(CuratorFrameworkCreate.getCuratorFramework(),
                        "/testBarrier");
                try {
                    log.info(Thread.currentThread().getName() + " 进入等待");
                    distributedBarrier.setBarrier();
                    distributedBarrier.waitOnBarrier();
                    log.info(Thread.currentThread().getName() + " 执行操作");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DistributedBarrier distributedBarrier = new DistributedBarrier(CuratorFrameworkCreate.getCuratorFramework(),
                "/testBarrier");
        try {
            log.info("执行所有线程操作");
            distributedBarrier.removeBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
