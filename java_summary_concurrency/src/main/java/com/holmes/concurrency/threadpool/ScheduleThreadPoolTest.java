package com.holmes.concurrency.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/17 15:14
 * @Version: 1.0.0
*/
public class ScheduleThreadPoolTest {

    public static void main(String[] args) {

        /**
         * 支持定时及周期性任务执行的定长线程池
         */
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 10; i++) {

            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 1L, 3L, TimeUnit.SECONDS);
        }
    }
}
