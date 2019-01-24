package com.holmes.concurrency.producerandconsumer;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 消费者
 * @Author: holmes
 * @Version: 1.0.0
 */
public class Consumer implements Runnable {

    private List<Data> dataList;

    public Consumer(List<Data> dataList) {
        this.dataList = Objects.requireNonNull(dataList);
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                Data data = null;
                synchronized (dataList) {
                    while (dataList.size() == 0) {
                        dataList.wait();
                    }
                    data = dataList.remove(0);
                    System.out.println(Thread.currentThread().getId() + "线程消费了：" + data);
                    dataList.notifyAll();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
