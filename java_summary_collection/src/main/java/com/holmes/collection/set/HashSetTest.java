package com.holmes.collection.set;

import com.holmes.collection.MyData;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 */
public class HashSetTest {

    @Test
    public void HashSetTest() {

        Set<MyData> set = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            set.add(new MyData(i % 1000 + "", "name" + System.currentTimeMillis() % 999));
        }

        //成员可以为null，且只有一个
        set.add(null);
        set.add(null);

        System.out.println("length = " + set.size());

        //遍历hashSet 遍历结果无序
        Iterator<MyData> iterator = set.iterator();
        while (iterator.hasNext()) {
            MyData data = iterator.next();
            System.out.println(data);
        }
    }
}
