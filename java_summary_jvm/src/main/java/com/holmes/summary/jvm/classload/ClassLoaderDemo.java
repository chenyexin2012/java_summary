package com.holmes.summary.jvm.classload;

public class ClassLoaderDemo {

    static {
        System.out.println("ClassLoaderDemo static block");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class.forName("com.holmes.summary.jvm.classload.ClassA");
        Class.forName("com.holmes.summary.jvm.classload.ClassB");

//        System.out.println(ClassA.a);
//        System.out.println(ClassB.a);

//        new ClassLoaderDemo();
    }


    public ClassLoaderDemo() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MyClassLoader classLoader = new MyClassLoader();

//        String str = "";
//        System.out.println(str.getClass().getClassLoader());

        ClassA classA1 = new ClassA();

        Class clazz = classLoader.findClass("com.holmes.summary.jvm.classload.ClassA");
        Object classA2 = clazz.newInstance();

        System.out.println(classA1.getClass().getClassLoader());
        System.out.println(classA2.getClass().getClassLoader());
    }
}
