package com.holmes.learn.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * @author Administrator
 */
public class ThreadPoolTest {

    private AtomicLong count = new AtomicLong();

    /**
     * 测试corePoolSize、maximumPoolSize以及workQueue的长度
     */
    @Test
    public void test() {

        ExecutorService service = new ThreadPoolExecutor(2, 3, 100, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(5),
                new ThreadFactory() {

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "Thread");
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("当前workQueue的长度为：" + executor.getQueue().size());
                    }
                });

        for (int i = 1; i <= 10; i++) {

            System.out.println("提交第" + i + "个线程");

            service.execute(new Runnable() {
                @Override
                public void run() {
                    long tmp = count.incrementAndGet();
                    System.out.println("启动第" + tmp + "个线程");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("第" + tmp + "个线程结束运行");
                }
            });
        }
    }

    /**
     * 当活跃线程超过最大线程数量且workQueue队列已满时，继续提交任务需要交给RejectedExecutionHandler
     * 无论是抛出异常还是开启线程在多数情况下都不是一种好方法。因此可以在rejectedExecution方法中使用
     * executor.getQueue().put(r)在阻塞任务提交，即可以防止启动太多线程，又不会漏掉任何一个任务。
     */
    @Test
    public void test2() {

        ExecutorService service = new ThreadPoolExecutor(2, 3, 100, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "Thread");
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
                });

        for (int i = 1; i <= 100; i++) {

            System.out.println("提交第" + i + "个线程");

            service.execute(new Runnable() {
                @Override
                public void run() {
                    long tmp = count.incrementAndGet();
                    System.out.println("启动第" + tmp + "个线程");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + tmp + "个线程结束运行");
                }
            });
        }
    }

    /**
     * 测试corePoolSize为0时
     */
    @Test
    public void test3() {

        ExecutorService service = new ThreadPoolExecutor(0, 3, 100, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactory() {

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "Thread");
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("当前workQueue的长度为：" + executor.getQueue().size());
                    }
                });


        for (int i = 1; i <= 10; i++) {

            System.out.println("提交第" + i + "个线程");

            service.execute(new Runnable() {
                @Override
                public void run() {
                    long tmp = count.incrementAndGet();
                    System.out.println("启动第" + tmp + "个线程");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("第" + tmp + "个线程结束运行");
                }
            });
        }
    }


}
