package com.holmes.concurrency.executor;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/22 11:04
 * @Version: 1.0.0
 */
public class AppTest {

    private static final int TASK_COUNT = 5;

    private static final int THREAD_COUNT = 10;

    @Test
    public void simpleExecutorTest() {

        Executor executor = new SimpleExecutor();

        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Random random = new Random();
                        long workingTime = random.nextInt(10000) + 1;
                        Thread.sleep(workingTime);
                        System.out.println(String.valueOf(index) + " " + String.valueOf(workingTime)
                                + " " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleThreadPoolExecutorTest() {

        Executor executor = new SimpleThreadPoolExecutor(3);

        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 3; i++) {
                            System.out.println(Thread.currentThread() + " " + index);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
