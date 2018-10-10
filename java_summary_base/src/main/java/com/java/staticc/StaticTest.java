package com.java.staticc;

public class StaticTest {

    public static int value = 1;

    public void show() {
        // java中允许使用this访问静态变量
        System.out.println(this.value);
        // java中允许使用this访问静态方法
        this.staticShow();
    }

    public static void staticShow() {
        // 静态方法中不能使用this关键字
        // System.out.println(this.value);
        System.out.println(value);
    }

    public static void main(String[] args) {

        StaticTest test = new StaticTest();
        // java中允许使用对象访问静态变量
        System.out.println(test.value);
        System.out.println(test.value);
        test.show();
        // java中允许使用对象访问静态方法
        test.staticShow();
        StaticTest.staticShow();
    }
}
