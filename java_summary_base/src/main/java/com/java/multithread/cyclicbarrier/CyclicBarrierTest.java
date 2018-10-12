package com.java.multithread.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

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
