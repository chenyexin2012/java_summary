package com.holmes.concurrency.producerandconsumer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 消费者
 * @Author: holmes
 * @Version: 1.0.0
 */
public class Producer implements Runnable {

    private final static AtomicInteger ID = new AtomicInteger(0);

    private List<Data> dataList;

    private int capacity;

    public Producer(List<Data> dataList, int capacity) {
        this.dataList = Objects.requireNonNull(dataList);
        this.capacity = capacity;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                synchronized (dataList) {
                    while (dataList.size() >= capacity) {
                        dataList.wait();
                    }
                    Data data = new Data(ID.getAndIncrement(), String.valueOf(System.currentTimeMillis()));
                    dataList.add(data);
                    System.out.println(Thread.currentThread().getId() + "线程生产了：" + data);
                    dataList.notifyAll();
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
