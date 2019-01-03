package com.holmes.concurrency.thread;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
public class JoinTestDemo {

    public static void main(String[] args) {

        final StringBuilder sb = new StringBuilder();

        Thread thread = new Thread(new Runnable() {
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
        thread.start();

        try {
            // 放弃执行当前线程，等待thread返回
            thread.join();
            System.out.println(sb.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
