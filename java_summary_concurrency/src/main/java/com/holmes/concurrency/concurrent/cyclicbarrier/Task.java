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
        System.out.println(taskName + " 执行中。。。");
        for (int i = 0; i < 99999; i++){

        }
        try {
            // 通知障碍器
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
