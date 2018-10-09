package com.java.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {


    @Test
    public void HashSetTest() {

        Set<MyData> set = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            set.add(new MyData(i % 1000 + "",
                    "name" + System.currentTimeMillis() % 999));
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

    @Test
    public void TreeSetTest() {
//		Set<MyData> set = new TreeSet<MyData>(new Comparator<MyData>() {
//			@Override
//			public int compare(MyData o1, MyData o2) {
//				
//				if(o1 == null && o2 == null) return 0;
//				if(o1 == null && o2 != null) return -1;
//				if(o1 != null && o2 == null) return 1;
////				return o1.getId().compareTo(o2.getId()) == 0 ? o1.getName().compareTo(o2.getName()) : 
////					o1.getId().compareTo(o2.getId());
//				//根据name字段排序
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
        TreeSet<MyData> set = new TreeSet<MyData>();

        for (int i = 0; i < 100; i++) {
            set.add(new MyData(System.nanoTime() % 1000 + "",
                    "name" + System.nanoTime() % 999));
        }

//		set.add(null); //对象不能为null
        System.out.println("length = " + set.size());

        //遍历hashSet 遍历结果有序
        for (MyData data : set) {
            System.out.println(data);
        }
    }

    @Test
    public void LinkedHashSetTest() {

        Set<MyData> set = new java.util.LinkedHashSet<>();

        for (int i = 0; i < 50; i++) {
            set.add(new MyData(i + "",
                    "name" + System.nanoTime() % 1000));
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