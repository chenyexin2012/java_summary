package com.holmes.collection.map;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Administrator
 */
public class HashTableTest {

    @Test
    public void HashTableTest() {
        Hashtable<Long, String> map = new Hashtable<>();

        for (int i = 0; i < 10; i++) {
            map.put(System.nanoTime() % 1000, System.nanoTime() + "");
        }
        // 键与值均不能为null
        // map.put(null, "key is null");
        // map.put(2L, null);

        System.out.println("length = " + map.size());

//		Iterator<Entry<Long, String>> iterator = map.entrySet().iterator();
//		while (iterator.hasNext()) {
//
//			Entry<Long, String> entry = iterator.next();
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}

        Enumeration<String> enumeration = map.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }
}
