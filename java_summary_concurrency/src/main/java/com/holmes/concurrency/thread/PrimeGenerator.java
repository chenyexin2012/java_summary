package com.holmes.concurrency.thread;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 使用一个中断标志来判断是否结束线程
 * @Author: holmes
 * @Version: 1.0.0
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new LinkedList<>();

    /**
     * volatile
     */
    private volatile boolean canceled = false;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        // 依靠标志位判断是否结束线程
        while (!canceled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    /**
     * 取消
     */
    public void cancel() {
        canceled = true;
    }

    public synchronized List<BigInteger> get() {
        return primes;
    }

    public static void main(String[] args) {

        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            primeGenerator.cancel();
        }
        System.out.println(primeGenerator.get());
    }
}