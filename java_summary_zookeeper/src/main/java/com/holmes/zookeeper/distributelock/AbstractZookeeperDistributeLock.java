package com.holmes.zookeeper.distributelock;

public abstract class AbstractZookeeperDistributeLock implements Lock {

    public void lock() {

        if(getLock()) {
            return;
        } else {
            waitLock();
            lock();
        }
    }

    public void unlock() {
        deleteNode();
    }

    /**
     * 获取锁
     */
    protected abstract boolean getLock();

    /**
     * 为获取到锁进入等待
     */
    protected abstract void waitLock();

    /**
     * 删除节点
     */
    protected abstract void deleteNode();
}
