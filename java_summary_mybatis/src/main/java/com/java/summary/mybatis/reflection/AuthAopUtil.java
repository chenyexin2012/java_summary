package com.java.summary.mybatis.reflection;

import com.java.summary.mybatis.annotation.AuthAop;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;

/**
 * AuthAop注解获取工具类
 *
 * @author Administrator
 */
public class AuthAopUtil {

    public static AuthAop getAuthAop(MappedStatement mappedStatement) {

        AuthAop authAop = null;
        String id = mappedStatement.getId();
        String className = id.substring(0, id.lastIndexOf('.'));
        String methodName = id.substring(id.lastIndexOf('.') + 1);

        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                // 同一Mapper中的方法不允许重载，故方法名不会重复
                if (methodName.equals(methods[i].getName()) && methods[i].isAnnotationPresent(AuthAop.class)) {
                    authAop = methods[i].getAnnotation(AuthAop.class);
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return authAop;
    }
}
