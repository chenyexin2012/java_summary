package com.holmes.concurrency.thread;

/**
 * @Description: 线程睡眠中被中断
 * @Author: holmes
 * @Version: 1.0.0
*/
public class SleepingInterrupted implements Runnable {

    @Override
    public void run() {

        System.out.println(Thread.currentThread() + "启动");
        System.out.println(Thread.currentThread() + "休眠5秒");
        try {
            // 休眠5秒
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + "休眠结束");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + "中断");
            // sleep()方法抛出异常，将清空中断标志，isInterrupted()方法返回false
            System.out.println(Thread.currentThread() + " isInterrupted=" + Thread.currentThread().isInterrupted());
            // 如果不调用return，则线程不会真正被中断，仍会继续执行
            return ;
        }
        System.out.println(Thread.currentThread() + "运行结束");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new SleepingInterrupted());
        thread.start();

        Thread.sleep(2000);

        System.out.println(thread + " isInterrupted=" + thread.isInterrupted());
        System.out.println(Thread.currentThread() + "中断线程thread");
        // 中断线程thread
        thread.interrupt();
        System.out.println(thread + " isInterrupted=" + thread.isInterrupted());

        System.out.println(Thread.currentThread() + "运行结束");
    }
}
