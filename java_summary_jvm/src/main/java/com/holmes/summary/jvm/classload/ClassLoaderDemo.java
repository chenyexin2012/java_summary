package com.holmes.summary.jvm.classload;

import java.util.LinkedList;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        new ClassLoaderDemo();
    }


    public ClassLoaderDemo() {

        Thread.currentThread().setContextClassLoader(new MyClassLoader());

        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").toString());

        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add(new String("111"));
        linkedList.add(new String("222"));
        linkedList.add(new String("222"));
    }
}
