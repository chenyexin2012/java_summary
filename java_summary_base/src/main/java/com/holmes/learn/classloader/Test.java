package com.holmes.learn.classloader;

import com.holmes.learn.reflect.Student;

public class Test {

    public static void main(String[] args) {

        MyClassLoader myClassLoader = new MyClassLoader();

        try {
            Class<?> clazz = myClassLoader.findClass(
                    "com.holmes.learn.reflect.Student");

            Student student = (Student) clazz.newInstance();

            System.out.println(student);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
