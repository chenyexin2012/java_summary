package com.holmes.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    @org.junit.Test
    public void testName() throws Exception {

        AtomicInteger integer = new AtomicInteger();

        integer.incrementAndGet();
    }

    @org.junit.Test
    public void testTask() throws InterruptedException, ExecutionException {

        Task<TaskInfo> myTask1 = new MyTask("抓取网页数据");
        myTask1.start();

        Task<TaskInfo> myTask2 = new MyTask("调用第三方接口");
        myTask2.start();

        if (myTask1.get().isFinished()) {
            System.out.println("MyTask1 finished...");
        }

        if (myTask2.get().isFinished()) {
            System.out.println("MyTask2 finished...");
        }
    }

    @org.junit.Test
    public void testTTask() {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<TTask> tTaskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tTaskList.add(new TTask(String.valueOf((10 - i)), i * 1000L));
        }

        try {
            List<Future<TaskInfo>> futureList = executor.invokeAll(tTaskList);

            for (Future<TaskInfo> future : futureList) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
