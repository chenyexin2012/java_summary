package com.holmes.concurrency.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
 */
public class AtomicReferenceArrayDemo {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        AtomicReferenceArray<User> atomicReferenceArray = new AtomicReferenceArray<>(10);
        for (int i = 0; i < 10; i++) {
            atomicReferenceArray.set(i, new User("user" + i, 0));
        }

        for (int i = 0; i < 100; i++) {
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        for (int j = 0; j < 10; j++) {
                            while (true) {
                                User m = atomicReferenceArray.get(j);
                                User n = new User(m.getValue(), m.getBalance() + 1);
                                if (atomicReferenceArray.compareAndSet(j, m, n)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            });
        }
        EXECUTOR_SERVICE.shutdown();
        while (!EXECUTOR_SERVICE.isTerminated()) {
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(atomicReferenceArray.get(i));
        }
    }
}
