package com.holmes.concurrency.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/19 14:11
 * @Version: 1.0.0
 */
public class ExecutorCompletionServiceTest {

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
    public void executorCompletionServiceTest1() {

        CompletionService completionService = new ExecutorCompletionService<String>(executor);

        for (int i = 0; i < TASK_COUNT; i++) {

            final int index = i;
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    Random random = new Random();
                    long workingTime = random.nextInt(10000) + 1;
                    Thread.sleep(workingTime);
                    return String.valueOf(index) + " " + String.valueOf(workingTime) + " "
                            + Thread.currentThread().getName() + " finished...";
                }
            });
        }

        for (int i = 0; i < TASK_COUNT; i++) {
            try {
                Future<String> future = completionService.take();
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
