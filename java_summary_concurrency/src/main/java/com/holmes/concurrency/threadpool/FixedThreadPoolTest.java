package com.holmes.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/17 15:05
 * @Version: 1.0.0
*/
public class FixedThreadPoolTest {

    public static void main(String[] args) {

        /**
         * 使用指定工作线程数量的线程池
         * new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);

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

    }
}
