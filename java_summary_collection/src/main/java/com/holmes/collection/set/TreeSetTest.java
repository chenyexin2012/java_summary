package com.holmes.collection.set;

import com.holmes.collection.MyData;
import org.junit.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Administrator
 */
public class TreeSetTest {

    @Test
    public void TreeSetTest() {

        // 使用外部排序器
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

        TreeSet<MyData> set = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            set.add(new MyData(System.nanoTime() % 1000 + "", "name" + System.nanoTime() % 1000));
        }

//		set.add(null); //对象不能为null
        System.out.println("length = " + set.size());

        //遍历hashSet 遍历结果有序
        for (MyData data : set) {
            System.out.println(data);
        }
    }
}
