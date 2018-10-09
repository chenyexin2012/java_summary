package com.holmes.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletionServiceDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService);

        for (int i = 0; i < 10; i++) {

            final int j = i;
            completionService.submit(new Callable<Long>() {

                @Override
                public Long call() throws Exception {
                    return randLong(j);
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            Future<Long> futureTask = null;
            try {
                futureTask = completionService.take();

                System.out.println(futureTask.get(1, TimeUnit.NANOSECONDS));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                if (null != futureTask)
                    futureTask.cancel(true);
                System.out.println();
            }
        }
        executorService.shutdown();
    }

    private static Long randLong(int j) {

        try {
            Thread.sleep(1000 * (10 - j));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Long.valueOf(j);
    }
}
