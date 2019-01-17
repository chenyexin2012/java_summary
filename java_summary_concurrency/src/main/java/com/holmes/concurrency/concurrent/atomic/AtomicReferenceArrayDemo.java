package com.holmes.concurrency.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicReferenceArrayDemo {

    public static void main(String[] args) {

        AtomicReferenceArray<StringBuilder> atomicReferenceArray = new AtomicReferenceArray<>(10);

        for (int i = 0; i < 10; i++) {
            atomicReferenceArray.set(1, new StringBuilder("StringBuilder").append(i + 1));
        }

    }
}
