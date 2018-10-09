package com.holmes.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private static Lock lock = new ReentrantLock();

    private static int value = 0;

    private static int addValue() {

        lock.lock();
        value++;
        lock.unlock();

        return value;
    }

//	private static AtomicInteger value = new AtomicInteger();
//	
//	private static int addValue(){
//		return value.incrementAndGet();
//	}

//	private static int value = 0;
//	
//	private static synchronized int addValue(){
//		return value ++;
//	}


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        addValue();
                    }
                }
            });
        }

//		int count = ((ThreadPoolExecutor)executorService).getActiveCount();
//		
//		while (count > 0) {
//			count = ((ThreadPoolExecutor)executorService).getActiveCount();
//		}

        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }

        System.out.println(value);


    }
}
