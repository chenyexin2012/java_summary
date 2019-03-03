package com.holmes.xml.reflection;

import java.lang.reflect.Field;

/**
 * @author Administrator
 */
public class ReflectionUtils {

    public static Object newInstance(String className) {
        Class<?> clazz = null;
        Object object = null;
        try {
            clazz = Class.forName(className);
            object = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void setFieldValue(Object object, String fieldName, String fieldValue) {
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field.getType().isAssignableFrom(Integer.class)) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(object, Integer.valueOf(fieldValue));
            } else if (field.getType().isAssignableFrom(String.class)) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(object, (String) fieldValue);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
