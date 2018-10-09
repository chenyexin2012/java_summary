package com.holmes.learn.thread.threadPool;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends AbstractExecutorService {

    private ThreadPoolExecutor threadPoolExecutor = null;

    public MyThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void shutdown() {
        this.threadPoolExecutor.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.threadPoolExecutor.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return this.threadPoolExecutor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.threadPoolExecutor.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.threadPoolExecutor.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        this.threadPoolExecutor.execute(command);
    }

}
