package com.holmes.concurrency.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
 */
public class AtomicIntegerFieldUpdaterDemo {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        Data data = new Data();

        AtomicIntegerFieldUpdater<Data> dataAtomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(Data.class, "publicVar");

        for (int i = 0; i < 100; i++) {
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        while (true) {
                            int m = dataAtomicIntegerFieldUpdater.get(data);
                            int n = m + 1;
                            if (dataAtomicIntegerFieldUpdater.compareAndSet(data, m, n)) {
                                break;
                            }
                        }
                    }
                }
            });
        }
        EXECUTOR_SERVICE.shutdown();
        while (!EXECUTOR_SERVICE.isTerminated()) {
        }

        System.out.println(data);
    }
}
