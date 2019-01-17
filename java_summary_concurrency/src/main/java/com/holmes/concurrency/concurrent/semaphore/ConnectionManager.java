package com.holmes.concurrency.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 通过信号量控制访问getConnection方法的线程数量
 * @Author: holmes
 * @CreateDate: 2019/1/17 20:22
 * @Version: 1.0.0
 */
public class ConnectionManager {

    private static ConnectionManager connectionManager = new ConnectionManager();

    /**
     * 同时只能有个线程访问
     */
    private final Semaphore semaphore = new Semaphore(10);

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public MyConnection getConnection() {

        MyConnection connection = null;
        try {
            semaphore.acquire();
            connection = new MyConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        semaphore.release();
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ConnectionManager.getConnectionManager().getConnection();
                    System.out.println(Thread.currentThread().getName() + "获取到连接。。。");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ConnectionManager.getConnectionManager().close();
                }
            });
            executorService.shutdown();
        }
    }
}
