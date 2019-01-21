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

        Class<AnnotationDemo> annotation = AnnotationDemo.class;
        System.out.println(annotation.getName());
//        // 获取类注解
//        Annotation[] annotations = annotation.getAnnotations();
//        for(Annotation a : annotations) {
//            System.out.println(a.annotationType());
//        }

        // 判断类是否含有ClassAnnotation注解
        if(annotation.isAnnotationPresent(ClassAnnotation.class)) {
            ClassAnnotation classAnnotation = annotation.getAnnotation(ClassAnnotation.class);
            System.out.println(classAnnotation.value());
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Method[] methods = annotation.getDeclaredMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
//            // 获取方法注解
//            annotations = method.getAnnotations();
//            for(Annotation a : annotations) {
//                System.out.println(a.annotationType());
//            }
            // 判断方法中是否含有MehodAnnotation注解
            if(method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
                System.out.println(methodAnnotation.level());
                System.out.println(methodAnnotation.name());
                System.out.println(methodAnnotation.status());
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Field[] fields = annotation.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.getName());
//            // 获取方法注解
//            annotations = field.getAnnotations();
//            for(Annotation a : annotations) {
//                System.out.println(a.annotationType());
//            }

            // 判断属性中是否含有FieldAnnotation注解
            if(field.isAnnotationPresent(FieldAnnotation.class)) {
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.println(fieldAnnotation.value());
            }
        }
    }
}
