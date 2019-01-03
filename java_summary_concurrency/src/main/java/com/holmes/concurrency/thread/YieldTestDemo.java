package com.holmes.concurrency.thread;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
public class YieldTestDemo implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getId() + " " + i);
            if(i % 30 == 0) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {

        // 非单核心CPU环境下，无法观察运行结果
        Thread thread1 = new Thread(new YieldTestDemo());
        Thread thread2 = new Thread(new YieldTestDemo());

        thread1.start();
        thread2.start();
    }
}
