package com.holmes.collection.list;

import com.holmes.collection.MyData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Administrator
 */
public class ArrayListTest {

    @Test
    public void arrayListTest() {

        // 建议初始化时指定初始容量
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
}
