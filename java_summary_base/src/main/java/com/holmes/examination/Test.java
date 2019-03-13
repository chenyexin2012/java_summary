package com.holmes.examination;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    @org.junit.Test
    public void test() {

        AtomicInteger a = new AtomicInteger(1);
        AtomicInteger b = new AtomicInteger(2);

        change(a, b);
        System.out.println(a.intValue());
        System.out.println(b.intValue());

        swap(a, b);
        System.out.println(a.intValue());
        System.out.println(b.intValue());
    }

    private void change(AtomicInteger a, AtomicInteger b) {
        a.incrementAndGet();
        b.incrementAndGet();
    }

    private void swap(AtomicInteger a, AtomicInteger b) {
        AtomicInteger temp = a;
        a = b;
        b = temp;
    }
}
