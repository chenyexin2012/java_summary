package com.holmes.learn.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<String>();

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
    }
}
