package com.holmes.concurrency.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: 通过实现Callable接口创建线程
 * @Author: holmes
 * @Version: 1.0.0
 */
public class CallableDemo implements Callable<String> {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private int count = 10;

    @Override
    public String call() throws Exception {

        while (count > 0) {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + ", count = " + count--);
        }

        return Thread.currentThread().getName() + " return...";
    }


    public static void main(String[] args) {

        CallableDemo callable = new CallableDemo();
        List<Future> futureList = new ArrayList<>(10);

        for(int i = 0; i < 3; i++) {
            Future future = executorService.submit(callable);
            futureList.add(future);
        }
        executorService.shutdown();

        for(Future future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
