package com.holmes.concurrency.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/17 15:37
 * @Version: 1.0.0
*/
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        final AtomicInteger count = new AtomicInteger(0);
        /**
         *
         1.corePoolSize：表示一个线程池的核心线程数，当活线程数小于corePoolSize，每提交一个任务就会开启一个线程来处理。

         2.maximumPoolSize：maximumPoolSize>=corePoolSize，表示线程池中最多能够活跃的线程数，当workQueue长度有限且
         放入线程数量已满时，会继续开启线程，直到活跃线程数超过maximumPoolSize。因此，当workQueue的未指定长度时，
         maximumPoolSize的值将无效，因为workQueue不会满。

         3.keepAliveTime：表示超过corePoolSize数量的线程处于空闲状态的最长时间，可以allowCoreThreadTimeOut(true)使
         得keepAliveTime可以作用于活跃线程。

         4.timeUnit：keepAliveTime的时间单位，如TimeUnit.MINUTES，表示分钟。

         5.workQueue：阻塞任务队列

         6.ThreadFactory：是一个线程工厂，可以自定义生成的线程，如自定义线程名称。

         7.RejectedExecutionHandler：当提交的任务数超过maxmumPoolSize与workQueue之和时，如何处理任务会交给
         RejectedExecutionHandler来进行。

         */
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    int m = count.getAndIncrement();
                    String threadName = "Thread-" + m;
                    System.out.println("create thread " + threadName);
                    return new Thread(r, threadName);
                }
            },
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " run...");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPoolExecutor.shutdown();
        while(!threadPoolExecutor.isTerminated()) {
        }
        System.out.println("all threads had finished ...");
    }
}
