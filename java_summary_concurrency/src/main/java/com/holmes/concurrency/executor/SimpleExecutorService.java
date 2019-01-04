package com.holmes.concurrency.executor;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2019/1/4 19:55
 * @Version: 1.0.0
 */
public class SimpleExecutorService implements ExecutorService {

    private final static int DEFAULT_THREAD_NUM = 8;

    private int poolSize = DEFAULT_THREAD_NUM;

    private List<TaskThread> threadList;

    private BlockingQueue<Runnable> taskQueue;

    private volatile boolean isShutdown = false;

    private volatile boolean isTerminated = false;

    public SimpleExecutorService() {
        this(DEFAULT_THREAD_NUM);
    }

    public SimpleExecutorService(int poolSize) {
        this.poolSize = poolSize;
        this.threadList = new ArrayList<>(poolSize);
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void shutdown() {
        this.isShutdown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    @Override
    public boolean isTerminated() {
        return this.isTerminated;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                         long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {

        return null;
    }

    @Override
    public void execute(Runnable command) {

        if(this.threadList.size() < this.poolSize) {
            TaskThread thread = new TaskThread();
            thread.start();
            this.threadList.add(thread);
        }
        if(!this.taskQueue.offer(command)) {
            System.out.println("任务队列已满！");
        }
    }

    private class TaskThread extends Thread {
        @Override
        public void run() {
            while(true) {
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
