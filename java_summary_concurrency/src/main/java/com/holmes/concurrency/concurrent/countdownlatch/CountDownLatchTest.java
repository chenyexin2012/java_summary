package com.holmes.concurrency.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(new Task("task 1", countDownLatch)).start();
        new Thread(new Task("task 2", countDownLatch)).start();
        new Thread(new Task("task 3", countDownLatch)).start();

        try {
            // 等待任务完成
            countDownLatch.await();
            System.out.println("所有线程执行完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
