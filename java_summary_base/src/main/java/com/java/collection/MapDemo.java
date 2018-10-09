package com.java.collection;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {

    @Test
    public void HashMapTest() {

        Map<Long, String> map = new HashMap<Long, String>();

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
        Iterator<Entry<Long, String>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Entry<Long, String> entry = iterator2.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        long time3 = System.nanoTime();
        //////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////
        //遍历方式3
        Set<Entry<Long, String>> entry = map.entrySet();
        for (Entry<Long, String> entry2 : entry) {
            System.out.println(entry2.getKey() + " = " + entry2.getValue());
        }
        long time4 = System.nanoTime();
        //////////////////////////////////////////////////////////

        System.out.println("按key遍历：" + (time2 - time1));
        System.out.println("按entry(iterator)遍历：" + (time3 - time2));
        System.out.println("按entry遍历：" + (time4 - time3));
    }

    @Test
    public void hashTableTest() {
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

    @Test
    public void concurrentHashMapTest() {

        ConcurrentHashMap<Long, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(System.nanoTime() % 1000, System.nanoTime() + "");
        }
        // map.put(null, "key is null");
        // map.put(2L, null);

        System.out.println("length = " + map.size());

        Iterator<Entry<Long, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {

            Entry<Long, String> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 对比HashMap、HashTable、ConcurrentHashMap写入、取值速度
     */
    @Test
    public void compare() {

        Object object = new Object();

        long count = 10000;
        long time1 = 0L;
        long time2 = 0L;

        System.out.println("HashMap、HashTable、ConcurrentHashMap写入10000条数据所用时间");

        time1 = System.nanoTime();
        HashMap<Long, Object> hashMap = new HashMap<Long, Object>();
        for (long i = 0; i < count; i++) {
            hashMap.put(i, object);
        }
        time2 = System.nanoTime();
        System.out.println("HashMap：" + (time2 - time1));

        time1 = System.nanoTime();
        Hashtable<Long, Object> hashTable = new Hashtable<Long, Object>();
        for (long i = 0; i < count; i++) {
            hashTable.put(i, object);
        }
        time2 = System.nanoTime();
        System.out.println("Hashtable：" + (time2 - time1));

        time1 = System.nanoTime();
        ConcurrentHashMap<Long, Object> concurrentHashMap = new ConcurrentHashMap<Long, Object>();
        for (long i = 0; i < count; i++) {
            concurrentHashMap.put(i, object);
        }
        time2 = System.nanoTime();
        System.out.println("ConcurrentHashMap：" + (time2 - time1));


        ///////////////////////////////////////////////////////////////////////////////////
        System.out.println("HashMap、HashTable、ConcurrentHashMap以同种方法遍历所有数据所用时间");

        time1 = System.nanoTime();
        Iterator<Long> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Long key = iterator.next();
            hashMap.get(key);
        }
        time2 = System.nanoTime();
        System.out.println("HashMap：" + (time2 - time1));

        time1 = System.nanoTime();
        Iterator<Long> iterator2 = hashTable.keySet().iterator();
        while (iterator2.hasNext()) {
            Long key = iterator2.next();
            hashTable.get(key);
        }
        time2 = System.nanoTime();
        System.out.println("HashTable：" + (time2 - time1));

        time1 = System.nanoTime();
        Iterator<Long> iterator3 = concurrentHashMap.keySet().iterator();
        while (iterator3.hasNext()) {
            Long key = iterator3.next();
            concurrentHashMap.get(key);
        }
        time2 = System.nanoTime();
        System.out.println("ConcurrentHashMap：" + (time2 - time1));
    }
}
