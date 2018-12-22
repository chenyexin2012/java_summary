package com.holmes.concurrency.threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/17 14:56
 * @Version: 1.0.0
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {

        /**
         * 使用单个线程的线程池
         * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Random random = new Random();
                        long workingTime = random.nextInt(10000) + 1;
                        Thread.sleep(workingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
