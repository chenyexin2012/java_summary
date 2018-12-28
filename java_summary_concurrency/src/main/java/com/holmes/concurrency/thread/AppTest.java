package com.holmes.concurrency.thread;

import org.junit.Test;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/27 20:37
 * @Version: 1.0.0
*/
public class AppTest {

    /**
     * 使用一个中断标志来判断是否结束线程
     */
    @Test
    public void primeGeneratorTest() {

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
