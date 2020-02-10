package com.holmes.concurrency.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 通过障碍器来实现在一组任务均结束后来执行某个任务
 * @Author: holmes
 * @CreateDate: 2019/1/17 20:17
 * @Version: 1.0.0
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行所有任务！");
            }
        });
        new Thread(new Task("task 1", cb)).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new Task("task 2", cb)).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new Task("task 3", cb)).start();

        // 可以重复使用
//        cb.reset();
//        new Thread(new Task("task 1", cb)).start();
//        new Thread(new Task("task 2", cb)).start();
//        new Thread(new Task("task 3", cb)).start();
    }

}
