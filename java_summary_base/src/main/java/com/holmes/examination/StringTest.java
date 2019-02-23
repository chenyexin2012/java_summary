package com.holmes.examination;

/**
 * String 常见笔试面试要点
 *
 * @author Administrator
 */
public class StringTest {

    public static void main(String[] args) {

        // 使用字面量直接创建字符串对象，首先会去常量池中获取，如果获取的到，则直接返回
        // 否则创建一个新的对象放入常量池
        String a = "hello";
        String b = "hello";
        System.out.println(a == b);

        // 使用new创建，直接在堆上分配内存空间创建该对象
        String c = new String("hello");
        String d = new String("hello");
        System.out.println(a == c);
        System.out.println(c == d);


        String e = "hel";
        String f = "lo";
        // jvm会将 e + f 优化为StringBuilder的拼接，因此会生成新的对象
        String ef = e + f;
        System.out.println(a == ef);


        final String g = "hel";
        final String h = "lo";
        // 字符串常量拼接，jvm会在编译期将其替换为为常量，就相当于第一种方式，先去常量池查看
        String gh = g + h;
        System.out.println(gh == a);

        // intern 方法的作用是去字符串常量池查看是否存在该字符串对象（equals() 方法进行比较）
        // 如果存在，那么直接返回该对象的引用，如果不存在那么直接在常量池中创建该对象，并返回该对象的地址
        String i = new String("hello");
        System.out.println(a == i);
        System.out.println(a == i.intern());

        // new String(buf, true);
        String j = String.valueOf(255);
        String k = String.valueOf(255);
        System.out.println(j == k);
    }

}
