package com.holmes.zookeeper.clients.curator.atomic;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicValue;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DistributedAtomicValueDemo {

    private final static Logger log = LoggerFactory.getLogger(DistributedAtomicIntegerDemo.class);

    public static void main(String[] args) throws Exception {

        DistributedAtomicValue atomicValue = new DistributedAtomicValue(CuratorFrameworkCreate.getCuratorFramework(),
                "/atomicValue", new RetryNTimes(10, 100),
                PromotedToLock.builder().lockPath("/promotedToLock").timeout(3, TimeUnit.SECONDS).build());

        atomicValue.forceSet("Hello World".getBytes());

        AtomicValue<byte[]> value = atomicValue.get();
        log.info("pre value: {}", new String(value.preValue()));
        log.info("post value: {}", new String(value.postValue()));
    }
}
