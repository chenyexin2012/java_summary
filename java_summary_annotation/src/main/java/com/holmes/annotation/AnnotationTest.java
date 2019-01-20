package com.holmes.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
public class AnnotationTest {

    public static void main(String[] args) {

        Class annotation = AnnotationDemo.class;
        System.out.println(annotation.getName());
        // 获取类注解
        Annotation[] annotations = annotation.getAnnotations();
        for(Annotation a : annotations) {
            System.out.println(a.annotationType());
        }

        Method[] methods = annotation.getDeclaredMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
            // 获取方法注解
            annotations = method.getAnnotations();
            for(Annotation a : annotations) {
                System.out.println(a.annotationType());
            }
        }

        Field[] fields = annotation.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.getName());
            // 获取方法注解
            annotations = field.getAnnotations();
            for(Annotation a : annotations) {
                System.out.println(a.annotationType());
            }
        }
    }
}
