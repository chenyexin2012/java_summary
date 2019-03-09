package com.holmes.concurrency.concurrent.locks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 利用反射获取Unsafe对象
 *
 * @author Administrator
 */
public class UnsafeUtil {

    public static Unsafe getUnsafeInstance() {
        try {
            Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            return (Unsafe) theUnsafeInstance.get(Unsafe.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchFieldException, InstantiationException {

        /**
         * 偏移地址：某个属性在它的类的存储分配中的位置
         */

        Unsafe unsafe = getUnsafeInstance();

        // 通过allocateInstance初始化对象，直接为对象分配内存，
        // 不需要调用其构造函数、初始化代码、JVM安全检查等等，
        // 同时可以无视构造方法的修饰符。
        Unsafe unsafe1 = (Unsafe) unsafe.allocateInstance(Unsafe.class);

        System.out.println(unsafe == unsafe1);

        Class<Student> clazz = Student.class;

        // allocateInstance初试化student
        Student student = (Student) unsafe.allocateInstance(clazz);
        System.out.println(student);

        // 获取静态变量的偏移量
        Long offset = unsafe.staticFieldOffset(clazz.getDeclaredField("offset"));
        System.out.println(offset);
        System.out.println(unsafe.getObject(unsafe.staticFieldBase(clazz.getDeclaredField("offset")), offset));

        // 获取非静态变量的偏移量
        Long idOffset = unsafe.objectFieldOffset(clazz.getDeclaredField("id"));
        System.out.println(idOffset);
        Long nameOffset = unsafe.objectFieldOffset(clazz.getDeclaredField("name"));
        System.out.println(nameOffset);

        // 通过putObject方法更新对象student的中偏移量为12、16的字段的值，可以无视字段的修饰符。
        unsafe.putObject(student, idOffset, new Integer(1));
        unsafe.putObject(student, nameOffset, "holmes");
        System.out.println(student);

        // 通过偏移量获取对象值
        Integer id = (Integer) unsafe.getObject(student, idOffset);
        String name = (String) unsafe.getObject(student, nameOffset);
        System.out.println(id);
        System.out.println(name);

        // 通过compareAndSwapObject修改student中id的值，当内存中id值为期望值时，才修改id的值，并返回true，
        // 否则不修改且返回false
        System.out.println(unsafe.compareAndSwapObject(student, idOffset, id + 1, new Integer(100)));
        System.out.println(student);
        System.out.println(unsafe.compareAndSwapObject(student, idOffset, id, new Integer(100)));
        System.out.println(student);
    }

    private static class Student {

        private static Integer offset = 1000;

        private Integer id;

        private String name;

        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Student{");
            sb.append("id=").append(id == null ? "" : id);
            sb.append(", name='").append(name == null ? "null" : name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
