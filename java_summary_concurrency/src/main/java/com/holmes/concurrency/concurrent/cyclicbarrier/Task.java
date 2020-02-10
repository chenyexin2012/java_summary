package com.holmes.concurrency.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2019/1/17 20:19
 * @Version: 1.0.0
 */
public class Task implements Runnable {

    private String taskName;
    private CyclicBarrier cb;

    public Task(String taskName, CyclicBarrier cb) {
        this.taskName = taskName;
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            System.out.println(taskName + " 进入等待。。。");
            // 等待障碍器通知
            cb.await();
            System.out.println(taskName + " 开始执行。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
