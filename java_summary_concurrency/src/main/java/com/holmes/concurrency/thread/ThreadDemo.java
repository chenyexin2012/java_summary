package com.holmes.concurrency.thread;

/**
 * @Description: 使用继承Thread类实现线程
 * @Author: holmes
 * @Version: 1.0.0
 */
public class ThreadDemo extends Thread {

    private int count = 10;

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + ", count = " + count--);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 三个线程独立的执行代码，互不干扰
        new ThreadDemo().start();
        new ThreadDemo().start();
        new ThreadDemo().start();

        Thread.sleep(1000);
        System.out.println(">>>>>>>>>>>>>>>>>>>>");

        ThreadDemo threadDemo = new ThreadDemo();
        // 三个线程共享threadDemo对象
        new Thread(threadDemo).start();
        new Thread(threadDemo).start();
        new Thread(threadDemo).start();
    }
}
