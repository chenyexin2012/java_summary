package com.holmes.concurrency.thread;

/**
 * @Description: 守护线程
 * @Author: holmes
 * @Version: 1.0.0
*/
public class DaemonTestDemo implements Runnable {

    @Override
    public void run() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {

                    System.out.println("create a thread in daemon thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        thread.setDaemon(false);
        thread.start();

        // 在守护线程中创建的线程默认为守护线程，除非手动调用setDaemon方法设置为非守护线程
        System.out.println(thread.isDaemon());

        while(true) {
            System.out.println("this is a daemon thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new DaemonTestDemo());

        // 将线程设置为守护线程，且setDaemon必须在start之前，否则会抛出IllegalThreadStateException异常
        thread.setDaemon(true);
        thread.start();

        // 守护线程thread会在main线程运行结束后退出
        Thread.sleep(5000);
    }
}
