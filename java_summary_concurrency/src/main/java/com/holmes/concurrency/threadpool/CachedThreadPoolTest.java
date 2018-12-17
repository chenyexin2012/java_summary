package com.holmes.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/17 15:09
 * @Version: 1.0.0
*/
public class CachedThreadPoolTest {

    public static void main(String[] args) {

        /**
         * 可缓存线程池
         * return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            executorService.shutdown();
            while (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
            }
            System.out.println("all threads had finished ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
