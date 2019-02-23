package com.holmes.concurrency.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 */
public class Task implements Runnable {

    private String taskName;
    private CountDownLatch latch;

    public Task(String taskName, CountDownLatch latch) {
        this.taskName = taskName;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(taskName + " 执行中。。。");
        for (int i = 0; i < 99999; i++) {

        }
        System.out.println(taskName + " 任务执行完毕。。。");
        // 任务完成，将计数器减一
        latch.countDown();
        System.out.println(taskName + " 退出。。。");
    }
}