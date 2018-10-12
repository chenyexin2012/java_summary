package com.java.multithread.semaphore;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        // 线程获得执行许可
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "获得许可。。。");
                        // 模拟任务执行
                        int num = 0;
                        for (int j = 0; j < 10000; j++) {
                            num += j;
                        }
                        // 线程释放许可
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "释放许可。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
