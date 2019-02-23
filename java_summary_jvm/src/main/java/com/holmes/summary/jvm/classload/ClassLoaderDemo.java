package com.holmes.summary.jvm.classload;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        new ClassLoaderDemo();
    }


    public ClassLoaderDemo() {

        System.out.println(this.getClass().getClassLoader());
        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(this.getClass().getClassLoader());

    }
}
