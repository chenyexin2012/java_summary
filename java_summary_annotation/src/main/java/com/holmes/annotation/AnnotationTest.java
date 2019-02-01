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

        Class<AnnotationDemo> clazz = AnnotationDemo.class;

        // 获取类注解
        System.out.println("获取类" + clazz.getName() + "的所有注解>>>>>>>>>>>");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a.annotationType());
        }

        // 判断类是否含有ClassAnnotation注解
        System.out.println("\n获取类" + clazz.getName() + "的ClassAnnotation注解>>>>>>>>>>>");
        if (clazz.isAnnotationPresent(ClassAnnotation.class)) {
            ClassAnnotation classAnnotation = clazz.getAnnotation(ClassAnnotation.class);
            System.out.println(classAnnotation.value());
        }

        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 获取方法注解
            System.out.println("\n获取方法" + method.getName() + "的所有注解>>>>>>>>>>>");
            annotations = method.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a.annotationType());
            }
            // 判断方法中是否含有MethodAnnotation注解
            System.out.println("\n获取方法" + method.getName() + "的MethodAnnotation注解>>>>>>>>>>>");
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
                System.out.println(methodAnnotation.level());
                System.out.println(methodAnnotation.name());
                System.out.println(methodAnnotation.status());
            }

            // 获取方法参数的注解，返回一个二维数组
            System.out.println("\n获取方法" + method.getName() + "的所有参数注解>>>>>>>>>>>");
            Annotation[][] annotationTd = method.getParameterAnnotations();
            for (Annotation[] annotationOd : annotationTd) {
                for (Annotation a : annotationOd) {
                    if (a instanceof ParamAnnotation) {
                        System.out.println(((ParamAnnotation) a).value());
                    }
                }
            }
        }

        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 获取属性注解
            System.out.println("\n获取属性" + field.getName() + "的所有注解>>>>>>>>>>>");
            annotations = field.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a.annotationType());
            }

            // 判断属性中是否含有FieldAnnotation注解
            System.out.println("\n获取属性" + field.getName() + "的FieldAnnotation注解>>>>>>>>>>>");
            if (field.isAnnotationPresent(FieldAnnotation.class)) {
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.println(fieldAnnotation.value());
            }
        }
    }

}