package com.holmes.concurrency.producerandconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    private final static int MAX_SIZE = 10;

    private final static int CONSUMER_COUNT = 8;

    private final static int PRODUCER_COUNT = 2;

    public static void main(String[] args) {

        List<Data> dataList = new LinkedList<>();

        for (int i = 0; i < CONSUMER_COUNT; i++) {
            EXECUTOR.execute(new Consumer(dataList));
        }
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            EXECUTOR.execute(new Producer(dataList, MAX_SIZE));
        }
    }
}
