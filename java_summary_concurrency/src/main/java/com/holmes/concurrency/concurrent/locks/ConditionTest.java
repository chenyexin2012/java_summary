package com.holmes.concurrency.concurrent.locks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 使用Condition模拟生产者消费者
 * @Author: holmes
 * @Version: 1.0.0
*/
public class ConditionTest {

    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    private final Lock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    private List<String> dataList = new LinkedList<>();

    private final int MAX_SIZE = 10;

    private final int CONSUMER_COUNT = 8;

    private final int PRODUCER_COUNT = 2;

    public static void main(String[] args) {
        new ConditionTest();
    }

    ConditionTest() {

        for (int i = 0; i < CONSUMER_COUNT; i++) {
            EXECUTOR.execute(new Consumer());
        }
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            EXECUTOR.execute(new Producer());
        }
    }

    private class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (dataList.size() == MAX_SIZE) {
                        notFull.await();
                    }
                    String str = String.valueOf(System.currentTimeMillis());
                    dataList.add(str);
                    System.out.println(Thread.currentThread().getName() + "生产了：" + str);
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (dataList.size() == 0) {
                        notEmpty.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "消费了：" + dataList.remove(0));
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
