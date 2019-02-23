package com.holmes.concurrency.thread;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
public class JoinTestDemo {

    public static void main(String[] args) {

        final StringBuffer sb = new StringBuffer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    sb.append(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    sb.append(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    sb.append(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread3.start();

        try {
            // 放弃执行当前线程，等待thread返回
            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println(sb.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
