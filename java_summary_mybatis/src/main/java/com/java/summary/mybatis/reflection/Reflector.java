package com.java.summary.mybatis.reflection;

import java.lang.reflect.Field;

public class Reflector {

    /**
     * 获取object的私有属性
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getPrivateField(Object object, String fieldName) {

        Class clazz = object.getClass();
        Field field = null;

        while (clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
                if (null != field) {
                    break;
                }
            } catch (NoSuchFieldException e) {
            }
            clazz = clazz.getSuperclass();
        }

        if (field == null) {
            return null;
        }
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 为私有属性赋值
     *
     * @param object
     * @param fieldName
     * @param value
     * @return
     */
    public static boolean setPrivateField(Object object, String fieldName, Object value) {

        Class clazz = object.getClass();
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return false;
        }
        if (field == null)
            return false;
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            return false;
        }
        return true;
    }
}
