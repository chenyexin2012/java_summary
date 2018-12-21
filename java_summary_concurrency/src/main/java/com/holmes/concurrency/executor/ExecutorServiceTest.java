package com.holmes.concurrency.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        while(!executor.isTerminated()){
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
    public void testSubmitCallable() {

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



    public void invokeAllTest() {

    }

    public void invokeAnyTest() {

    }
}
