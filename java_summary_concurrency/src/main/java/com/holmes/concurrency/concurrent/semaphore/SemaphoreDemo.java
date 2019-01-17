package com.holmes.concurrency.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 通过信号量控制访问某个资源的线程数量
 * @Author: holmes
 * @CreateDate: 2019/1/17 20:21
 * @Version: 1.0.0
 */
public class SemaphoreDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 10; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 线程获得执行许可
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "获得许可。。。");
                        // 模拟任务执行
                        Thread.sleep(2000);
                        // 线程释放许可
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "释放许可。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.shutdown();
        }
    }
}
