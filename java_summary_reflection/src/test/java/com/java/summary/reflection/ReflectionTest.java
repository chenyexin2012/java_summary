package com.java.summary.reflection;

import com.java.summary.reflection.base.Student;
import org.junit.Test;

public class ReflectionTest {

    @Test
    public void test() {

        int num[] = new int[]{1, 2, 3};
        Class<?> clazz = num.getClass();
        System.out.println(clazz);
        System.out.println(clazz.getComponentType());

        Student student = new Student();
        clazz = student.getClass();
        System.out.println(clazz);
        System.out.println(clazz.getComponentType());
    }
}
