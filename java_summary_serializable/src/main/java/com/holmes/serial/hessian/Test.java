package com.holmes.serial.hessian;

import com.holmes.serial.Child;
import com.holmes.serial.Parent;

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

        HessianSerializable.serializable(child, path);
        Child child2 = (Child) HessianSerializable.deSerializable(path);

        System.out.println(child2.getId());
        System.out.println(child2.getName());
        System.out.println(((Parent) child2).getName());
        System.out.println(child2.getAge());
        System.out.println(child2.getCreateTime());

        System.out.println(child == child2);

    }
}
