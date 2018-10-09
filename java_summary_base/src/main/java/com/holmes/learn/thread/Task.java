package com.holmes.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public abstract class Task<T> {

    protected FutureTask<T> future = new FutureTask<T>(new Callable<T>() {
        @Override
        public T call() throws Exception {

            return runTask();
        }

    });

    protected Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public T get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    public abstract T runTask();
}
