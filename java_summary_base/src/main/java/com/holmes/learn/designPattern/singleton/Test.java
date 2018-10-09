package com.holmes.learn.designPattern.singleton;


public class Test {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " " + Singleton.getInstance().hashCode());
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " " + Singleton.getInstance().hashCode());
            }
        });
        thread.start();
        thread1.start();
    }
}
