package com.holmes.collection.set;

import com.holmes.collection.MyData;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 */
public class LinkedHashSet {

    @Test
    public void LinkedHashSetTest() {

        Set<MyData> set = new java.util.LinkedHashSet<>();

        for (int i = 0; i < 50; i++) {
            set.add(new MyData(i + "", "name" + System.nanoTime() % 1000));
        }

        set.add(null);    //对象可以为null

        System.out.println("length = " + set.size());

        //遍历LinkedHashSet 遍历结果按照插入顺序
        Iterator<MyData> iterator = set.iterator();
        while (iterator.hasNext()) {
            MyData data = iterator.next();
            System.out.println(data);
        }
    }
}
