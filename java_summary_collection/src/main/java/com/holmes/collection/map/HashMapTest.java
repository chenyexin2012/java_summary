package com.holmes.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public class HashMapTest {

    @Test
    public void HashMapTest() {

        Map<Long, String> map = new HashMap<>(1000);

        for (int i = 0; i < 1000; i++) {
            map.put(System.nanoTime(), System.nanoTime() + "");
        }
        map.put(null, "key is null");
        map.put(1001L, null);
        map.put(1002L, null);

        System.out.println("length = " + map.size());

        //////////////////////////////////////////////////////////
        long time1 = System.nanoTime();
        //遍历方式1
        Iterator<Long> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Long key = iterator.next();
            System.out.println(key + " = " + map.get(key));
        }
        long time2 = System.nanoTime();
        //////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////
        //遍历方式2
        Iterator<Map.Entry<Long, String>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Long, String> entry = iterator2.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        long time3 = System.nanoTime();
        //////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////
        //遍历方式3
        Set<Map.Entry<Long, String>> entry = map.entrySet();
        for (Map.Entry<Long, String> entry2 : entry) {
            System.out.println(entry2.getKey() + " = " + entry2.getValue());
        }
        long time4 = System.nanoTime();
        //////////////////////////////////////////////////////////

        System.out.println("按key遍历：" + (time2 - time1));
        System.out.println("按entry(iterator)遍历：" + (time3 - time2));
        System.out.println("按entry遍历：" + (time4 - time3));
    }
}
