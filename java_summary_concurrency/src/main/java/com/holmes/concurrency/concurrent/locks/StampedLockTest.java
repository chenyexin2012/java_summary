package com.holmes.concurrency.concurrent.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description: java类作用描述
 * @Author: holmes
 * @Version: 1.0.0
 */
public class StampedLockTest {

    private double x, y;

    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 乐观锁
     *
     * @return
     */
    double distanceFromOrigin() {
        // 获得一个乐观锁
        long stamp = sl.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        // 检查获取乐观锁后是否有其它写操作发生
        if (!sl.validate(stamp)) {
            // 没有则获取悲观锁
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     * 悲观锁
     *
     * @param newX
     * @param newY
     */
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                // 将读锁转为写锁
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}