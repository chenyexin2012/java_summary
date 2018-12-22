package com.holmes.concurrency.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/21 10:56
 * @Version: 1.0.0
 */
public class ExecutorServiceTest {

    private static final int TASK_COUNT = 20;

    private static final int THREAD_COUNT = 10;

    private static ExecutorService executor = null;

    @Before
    public void before() {
        executor = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @After
    public void after() {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    @Test
    public void executeTest() {

        // void execute(Runnable command);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动了一个线程...");
            }
        });
    }

    @Test
    public void submitRunnableTest() {
        try {
            // Future<?> submit(Runnable task);
            Future future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("启动了一个线程...");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            // 阻塞直到线程结束
            future.get();
            System.out.println("线程运行结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void submitRunnableAndGetResultTest() {
        try {
            final StringBuilder result = new StringBuilder();
            // F<T> Future<T> submit(Runnable task, T result);
            Future<StringBuilder> future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        result.append("result");
                        System.out.println("启动了一个线程...");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, result);
            // 阻塞直到线程结束
            StringBuilder futureResult = future.get();

            System.out.println(result);
            System.out.println(futureResult);
            System.out.println(result == futureResult);
            System.out.println("线程运行结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void submitCallableTest() {

        try {
            // <T> Future<T> submit(Callable<T> task);
            Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "result";
                }
            });
            // 阻塞直到线程结束
            String result = future.get();
            System.out.println(result);
            System.out.println("线程运行结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeAllTest() {

        List<Callable<String>> taskList = new LinkedList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            taskList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Random random = new Random();
                    long workingTime = random.nextInt(10000) + 1;
                    Thread.sleep(workingTime);
                    return String.valueOf(index) + " " + String.valueOf(workingTime) + " "
                            + Thread.currentThread().getName();
                }
            });
        }

        try {
            // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;
            // 所有任务执行完后返回
            List<Future<String>> futureList = executor.invokeAll(taskList);
            System.out.println("所有任务执行完成：" + futureList.size());
            for(Future<String> future : futureList) {
                String result = future.get();
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeAllWithTimeoutTest() {

        List<Callable<String>> taskList = new LinkedList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            taskList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Random random = new Random();
                    long workingTime = random.nextInt(10000) + 1;
                    Thread.sleep(workingTime);
                    return String.valueOf(index) + " " + String.valueOf(workingTime) + " "
                            + Thread.currentThread().getName();
                }
            });
        }

        try {
            // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            //        throws InterruptedException;
            // 所有任务执行完后返回，任务超时则会退出
            List<Future<String>> futureList = executor.invokeAll(taskList, 5000L, TimeUnit.MILLISECONDS);
            System.out.println("所有任务执行完成：" + futureList.size());
            for(Future<String> future : futureList) {
                if(!future.isCancelled()) {
                    String result = future.get();
                    System.out.println(result);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeAnyTest() {

        List<Callable<String>> taskList = new LinkedList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            taskList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Random random = new Random();
                    long workingTime = random.nextInt(10000) + 1;
                    Thread.sleep(workingTime);
                    return String.valueOf(index) + " " + String.valueOf(workingTime) + " "
                            + Thread.currentThread().getName();
                }
            });
        }

        try {
            // <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            //        throws InterruptedException, ExecutionException;
            // 任意一个任务成功后返回
            String result = executor.invokeAny(taskList);

            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeAnyWithTimeoutTest() {

        List<Callable<String>> taskList = new LinkedList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            final int index = i;
            taskList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Random random = new Random();
                    long workingTime = random.nextInt(10000) + 1;
                    Thread.sleep(workingTime);
                    return String.valueOf(index) + " " + String.valueOf(workingTime) + " "
                            + Thread.currentThread().getName();
                }
            });
        }

        try {
            // <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            //        throws InterruptedException, ExecutionException, TimeoutException;
            // 任意一个任务成功后返回
            String result = executor.invokeAny(taskList, 500L, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
