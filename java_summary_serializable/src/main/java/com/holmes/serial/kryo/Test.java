package com.holmes.serial.kryo;

import com.holmes.serial.Child;
import com.holmes.serial.Parent;
import com.holmes.serial.java.JdkSerializable;

import java.util.Date;

public class Test {

    public static void main(String[] args) {

        String path = "G://child.obj";

        Child child = new Child(100, "parent", new Date(), "child", 23);

        System.out.println(child.getId());
        System.out.println(child.getName());
        System.out.println(((Parent) child).getName());
        System.out.println(child.getAge());
        System.out.println(child.getCreateTime());

        System.out.println();

        KryoSerialized.serializable(child, path);
        Child child2 = (Child) KryoSerialized.deSerializable(path, child.getClass());

        System.out.println(child2.getId());
        System.out.println(child2.getName());
        System.out.println(((Parent) child2).getName());
        System.out.println(child2.getAge());
        System.out.println(child2.getCreateTime());

        System.out.println(child == child2);
    }
}
