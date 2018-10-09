package com.holmes.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

//		ThreadFactory threadFactory = (run) -> {
//			return new Thread(run, "thread for processing request");
//		};
//		ExecutorService executorService2 = new ThreadPoolExecutor(
//				0, 0, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }
}
