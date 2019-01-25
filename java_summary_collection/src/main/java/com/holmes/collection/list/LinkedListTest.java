package com.holmes.collection.list;

import com.holmes.collection.MyData;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Administrator
 */
public class LinkedListTest {

    @Test
    public void linkedListTest() {

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
