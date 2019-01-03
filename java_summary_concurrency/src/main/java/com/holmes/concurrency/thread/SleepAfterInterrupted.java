package com.holmes.concurrency.thread;

/**
 * @Description: 线程睡眠之前被中断
 * @Author: holmes
 * @Version: 1.0.0
*/
public class SleepAfterInterrupted implements Runnable{

    @Override
    public void run() {

        System.out.println(Thread.currentThread() + "启动");
        // 调用中断方法，此时线程不会立即中断，而是在调用sleep方法时中断
        Thread.currentThread().interrupt();
        // 线程被中断，中断标志为true
        System.out.println(Thread.currentThread() + " isInterrupted=" + Thread.currentThread().isInterrupted());

        // 此时若调用Thread的静态方法interrupted，会将中断标志重置为false，此时调用sleep方法时将不会抛出异常
        Thread.interrupted();
        System.out.println(Thread.currentThread() + " isInterrupted=" + Thread.currentThread().isInterrupted());

        System.out.println(Thread.currentThread() + "休眠5秒");
        try {
            // 休眠5秒
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + "休眠结束");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + "被中断");
            // sleep()方法抛出异常，将清空中断标志，isInterrupted()方法返回false
            System.out.println(Thread.currentThread() + " isInterrupted=" + Thread.currentThread().isInterrupted());
            // 如果不调用return，则线程不会真正被中断，仍会继续执行
            return ;
        }
        System.out.println(Thread.currentThread() + "运行结束");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new SleepAfterInterrupted());
        thread.start();
    }
}
