package com.holmes.concurrency.concurrent.locks;

import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;

public class ReentrantReadWriteLockTest {

    @Test
    public void readLockTest() {

        ReadWriteLock lock = new ReentrantReadWriteLock();
        try {
            lock.readLock().lock();
            // read ...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Test
    public void writeLockTest() {

        ReadWriteLock lock = new ReentrantReadWriteLock();
        try {
            lock.writeLock().lock();
            // write ...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}
