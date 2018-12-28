package com.holmes.concurrency.thread;

/**
 * @Description: 通过实现Runnable接口创建线程
 * @Author: holmes
 * @Version: 1.0.0
*/
public class RunnableDemo implements Runnable {

    private int count = 10;

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(Thread.currentThread().getName() + ", count = " + count--);
        }
    }

    public static void main(String[] args) {

        RunnableDemo runnable = new RunnableDemo();
        // 三个线程共享runnable对象
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
