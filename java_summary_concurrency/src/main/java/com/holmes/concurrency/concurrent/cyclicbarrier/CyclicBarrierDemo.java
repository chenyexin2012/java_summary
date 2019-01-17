package com.holmes.concurrency.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 通过障碍器来实现在一组任务均结束后来执行某个任务
 * @Author: holmes
 * @CreateDate: 2019/1/17 20:17
 * @Version: 1.0.0
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程执行完毕！");
            }
        });
        new Thread(new Task("task 1", cb)).start();
        new Thread(new Task("task 2", cb)).start();
        new Thread(new Task("task 3", cb)).start();
    }

}
