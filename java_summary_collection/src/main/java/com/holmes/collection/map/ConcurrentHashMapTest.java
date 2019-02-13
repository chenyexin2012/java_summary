package com.holmes.collection.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
public class ConcurrentHashMapTest {

    @Test
    public void concurrentHashMapTest() {

        ConcurrentHashMap<Long, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(System.nanoTime() % 1000, System.nanoTime() + "");
        }
        // map.put(null, "key is null");
        // map.put(2L, null);

        System.out.println("length = " + map.size());

        Iterator<Map.Entry<Long, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<Long, String> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
