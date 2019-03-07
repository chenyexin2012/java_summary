package com.holmes.examination;

/**
 * @author Administrator
 */
public class SynchronizedTest {

    private StringBuilder str = new StringBuilder("");

    public static void main(String[] args) throws InterruptedException {

        final SynchronizedTest synchronizedTest = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest.synchronizedMethod(synchronizedTest);
            }
        }).start();

        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest.synchronizedMethod(synchronizedTest);
            }
        }).start();

        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                SynchronizedTest synchronizedTest = new SynchronizedTest();
                synchronizedTest.method2(" 1");
            }
        }).start();

        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                SynchronizedTest synchronizedTest = new SynchronizedTest();
                synchronizedTest.method(" 2");
            }
        }).start();

        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                SynchronizedTest synchronizedTest = new SynchronizedTest();
                synchronizedTest.method(" 3");
            }
        }).start();
    }

    public SynchronizedTest() {
    }

    public synchronized static void synchronizedMethod(SynchronizedTest obj) {

        obj.str.append("4");
        try {
            Thread.sleep(2000);
            System.out.println(obj.str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method(String tail) {

        this.str = this.str.append(tail);
        try {
            Thread.sleep(6000);
            System.out.println(this.str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(String tail) {

        this.str = this.str.append(tail);
        try {
            Thread.sleep(6000);
            System.out.println(this.str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
