package com.java.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    @Test
    public void ArrayListTest() {

        ArrayList<MyData> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add(new MyData(String.valueOf(System.nanoTime() % 1000), String.valueOf(System.nanoTime() % 1000)));
        }

        System.out.println("length = " + list.size());

        Iterator<MyData> iterator = list.iterator();
        while (iterator.hasNext()) {
            MyData data = iterator.next();
            System.out.println(data);
        }
    }

    public void capacityTest(int capacity, int count) {

        Object object = new Object();

        List<Object> list = new ArrayList<>(capacity);

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(object);
        }
        long end = System.currentTimeMillis();
        System.out.println("初始化容量为" + capacity + "用时" + (end - start) + "ms");
    }

    @Test
    public void test() {

        final int N = 10000000;
        capacityTest(100, N);
        capacityTest(1000, N);
        capacityTest(10000, N);
        capacityTest(100000, N);
        capacityTest(1000000, N);
    }

    @Test
    public void LinkedListTest() {

        List<MyData> list = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            list.add(new MyData(String.valueOf(System.nanoTime() % 1000), String.valueOf(System.nanoTime() % 1000)));
        }

        System.out.println("length = " + list.size());

        Iterator<MyData> iterator = list.iterator();
        while (iterator.hasNext()) {
            MyData data = iterator.next();
            System.out.println(data);
        }
    }


}
