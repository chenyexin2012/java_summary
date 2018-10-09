package com.java.summary.relection.base;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Hello world!
 */
public class App {
    /**
     * 获取Class的三种方式
     */
    @Test
    public void test1() {
        //1.通过对象的getClass方法
        MyObject obj = new MyObject();
        Class obj1 = obj.getClass();
        System.out.println(obj1.getName());


        //2.通过Object类的静态属性class
        Class obj2 = MyObject.class;
        System.out.println(obj2.getName());

        System.out.println(obj1 == obj2);

        //3.通过Class的静态方法classForName()
        try {
            Class obj3 = Class.forName("com.holmes.learn.reflect.MyObject");
            System.out.println(obj3.getName());
            System.out.println(obj1 == obj3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取类的构造方法
     */
    @Test
    public void test2() {

        Class clazz = Student.class;
        System.out.println("获取Student的所有公有构造方法以及参数");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {

            System.out.print(constructor.getName() + "\t");
            Type[] types = constructor.getGenericParameterTypes();
            for (Type type : types) {
                System.out.print(type + "\t");
            }
            System.out.println();
        }

        System.out.println("获取Student的所有构造方法以及参数");
        constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {

            System.out.print(constructor.getName() + "\t");
            Type[] types = constructor.getGenericParameterTypes();
            for (Type type : types) {
                System.out.print(type + "\t");
            }
            System.out.println();
        }

        try {
            System.out.println("获取共有无参的构造方法");
            Constructor constructor = clazz.getConstructor(null);
            System.out.println(constructor.getName() + "\t");

            Object obj = constructor.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            System.out.println("获取私有有参的构造方法");
            Constructor constructor = clazz.getDeclaredConstructor(int.class);
            System.out.println(constructor);
            constructor.setAccessible(true);
            Object obj = constructor.newInstance(12);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 通过反射调用类的构造方法
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {

        Class clazz = MyObject.class;

        Constructor constructor = clazz.getDeclaredConstructor(String.class, String.class);

        constructor.setAccessible(true);

        MyObject object = (MyObject) constructor.newInstance("张三", "3215161654613216514");

        constructor.setAccessible(false);

        System.out.println(object.toString());
    }


    /**
     * 通过class获取类的字段
     */
    @Test
    public void test4() throws Exception {

        Class clazz = MyObject.class;
        System.out.println("获取MyObject所有的公有字段。。。");

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("获取MyObject所有的字段");
        //获取一个MyObject对象

        Field[] fielsAll = clazz.getDeclaredFields();
        for (Field field : fielsAll) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }
        //获取一个MyObject对象
        MyObject myObject = (MyObject) clazz.getConstructor().newInstance();

        System.out.println("为myObject对象赋值");
        System.out.println(myObject);
        Field nameField = clazz.getField("name");
        nameField.set(myObject, "周杰伦");

        Field phoneField = clazz.getField("phone");
        phoneField.set(myObject, "18655663366");

        //idCard为私有字段，需要解除私有限定
        Field idCardField = clazz.getDeclaredField("idCard");
        idCardField.setAccessible(true);
        idCardField.set(myObject, "3625556556665665");

        Field addressFiels = clazz.getDeclaredField("address");
        addressFiels.setAccessible(true);
        addressFiels.set(myObject, "安徽省合肥市");

        System.out.println(myObject);
    }

    /**
     * 通过class获取类的方法
     */
    @Test
    public void test5() throws Exception {

        Class<MyObject> clazz = MyObject.class;

        System.out.println("******************获取MyClass公有方法******************");
        Method[] methods = clazz.getMethods();
        int index = 1;
        for (Method method : methods) {
            System.out.print(index++ + ":" + method.getName() + "\t");
            Class[] classes = method.getParameterTypes();
            for (Class c : classes) {
                System.out.print(c.getName() + "\t");
            }
            System.out.println("返回值类型为：" + method.getReturnType().getSimpleName());
        }

        System.out.println("******************获取MyClass所有方法******************");
        index = 1;
        Method[] methodAll = clazz.getDeclaredMethods();
        for (Method method : methodAll) {
            System.out.print(index++ + ":" + method.getName() + "\t");
            Class[] classes = method.getParameterTypes();
            for (Class c : classes) {
                System.out.print(c.getName() + "\t");
            }
            System.out.println("返回值类型为：" + method.getReturnType().getSimpleName());
        }
        System.out.println("******************调用MyClass的私有方法******************");
        MyObject myObject = (MyObject) clazz.getConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("print");
        //print为私有方法，需要解除
        method.setAccessible(true);
        method.invoke(myObject);

        method = clazz.getDeclaredMethod("setIdCard", String.class);
        method.setAccessible(true);
        method.invoke(myObject, "231651613165151");
        System.out.println("******************调用MyClass的公有方法******************");
        method = clazz.getMethod("setName", String.class);
        method.invoke(myObject, "张学友");
        method = clazz.getMethod("setPhone", String.class);
        method.invoke(myObject, "15655996669");


        method = clazz.getMethod("toString");
        System.out.println(method.invoke(myObject));
    }


}
