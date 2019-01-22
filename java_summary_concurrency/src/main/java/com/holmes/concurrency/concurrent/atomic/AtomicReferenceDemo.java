package com.holmes.concurrency.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
 */
public class AtomicReferenceDemo {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        final AtomicReference<User> integerAtomicReference = new AtomicReference<>(new User("object", 0));

        for (int i = 0; i < 100; i++) {
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        while (true) {
                            User m = integerAtomicReference.get();
                            User n = new User(m.getValue(), m.getBalance() + 1);
                            if (integerAtomicReference.compareAndSet(m, n)) {
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

        System.out.println(integerAtomicReference.get());
    }

    private static class User {

        private String value;

        private Integer balance;

        public User(String value, Integer balance) {
            this.value = value;
            this.balance = balance;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("User{");
            sb.append("value='").append(value).append('\'');
            sb.append(", balance=").append(balance);
            sb.append('}');
            return sb.toString();
        }
    }
}
