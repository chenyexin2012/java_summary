package com.holmes.zookeeper.clients.curator.barrier;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedDoubleBarrierDemo {

    private final static Logger log = LoggerFactory.getLogger(DistributedDoubleBarrierDemo.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 5; i++) {

            executorService.submit(() -> {
                DistributedDoubleBarrier distributedBarrier = new DistributedDoubleBarrier(CuratorFrameworkCreate.getCuratorFramework(),
                        "/testBarrier", 5);
                try {
                    log.info(Thread.currentThread().getName() + " 进入等待");
                    // 阻塞直到所有成员都加入
                    distributedBarrier.enter();
                    log.info(Thread.currentThread().getName() + " 执行任务");
                    TimeUnit.SECONDS.sleep((long) (Math.random() * 10) + 3);
                    // 阻塞直到所有成员都离开
                    distributedBarrier.leave();
                    log.info(Thread.currentThread().getName() + " 离开");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // 每隔一秒启动一个线程
            TimeUnit.SECONDS.sleep(1);
        }
        executorService.shutdown();
    }
}
