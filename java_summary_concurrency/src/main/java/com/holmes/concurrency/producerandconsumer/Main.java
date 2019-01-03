package com.holmes.concurrency.producerandconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        List<Data> dataList = new LinkedList<>();
        int capacity = 10;

        Producer producer1 = new Producer(dataList, capacity);
        Producer producer2 = new Producer(dataList, capacity);
        Producer producer3 = new Producer(dataList, capacity);

        Consumer consumer1 = new Consumer(dataList);
        Consumer consumer2 = new Consumer(dataList);
        Consumer consumer3 = new Consumer(dataList);
        Consumer consumer4 = new Consumer(dataList);
        Consumer consumer5 = new Consumer(dataList);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(producer1);
        executorService.submit(producer2);
        executorService.submit(producer3);

        executorService.submit(consumer1);
        executorService.submit(consumer2);
        executorService.submit(consumer3);
        executorService.submit(consumer4);
        executorService.submit(consumer5);
    }
}
