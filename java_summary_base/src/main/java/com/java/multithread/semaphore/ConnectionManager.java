package com.java.multithread.semaphore;

import java.util.concurrent.Semaphore;

public class ConnectionManager {

    private static ConnectionManager connectionManager = new ConnectionManager();

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
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ConnectionManager.getConnectionManager().getConnection();
                    System.out.println(Thread.currentThread().getName() + "获取到连接。。。");
                    for (int j = 0; j < 10000000; j++){

                    }
                    ConnectionManager.getConnectionManager().close();
                }
            });
            thread.start();
        }
    }
}
