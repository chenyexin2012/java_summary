package com.holmes.concurrency.concurrent.locks;

import org.junit.Test;

import java.util.concurrent.locks.Lock;


public class ReentrantLockTest {

    @Test
    public void lockTest() {

        Lock lock = new ReentrantLock();

        try {
            lock.lock();
            doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void tryLockTest() {

        Lock lock = new ReentrantLock();

        if (lock.tryLock()) {
            try {
                doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            doOtherThings();
        }

    }

    @Test
    public void lockInterruptiblyTest() {

        Lock lock = new ReentrantLock();

        try {
            lock.lockInterruptibly();
            doSomething();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void doSomething() {
        System.out.println(Thread.currentThread().getName() + "成功获取锁！");
    }

    private void doOtherThings() {
        System.out.println(Thread.currentThread().getName() + "未获取锁，转而做其它事！");
    }
}
