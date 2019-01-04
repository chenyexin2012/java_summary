package com.holmes.concurrency.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: 一个简单的定长线程池
 * @Author: holmes
 * @CreateDate: 2019/1/4 19:55
 * @Version: 1.0.0
 */
public class SimpleThreadPoolExecutor implements Executor {

    private final static int DEFAULT_THREAD_NUM = 8;

    private int poolSize = DEFAULT_THREAD_NUM;

    private int threadCount;

    private BlockingQueue<Runnable> taskQueue;

    public SimpleThreadPoolExecutor() {
        this(DEFAULT_THREAD_NUM);
    }

    public SimpleThreadPoolExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.threadCount = 0;
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void execute(Runnable command) {

        if (this.threadCount < this.poolSize) {
            TaskThread thread = new TaskThread();
            thread.start();
            threadCount++;
        }
        if (!this.taskQueue.offer(command)) {
            System.out.println("任务队列已满！");
        }
    }

    private class TaskThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable command = taskQueue.take();
                    command.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
