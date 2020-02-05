package com.holmes.zookeeper.distributelock;

public interface Lock {

    void lock();

    void unlock();
}
