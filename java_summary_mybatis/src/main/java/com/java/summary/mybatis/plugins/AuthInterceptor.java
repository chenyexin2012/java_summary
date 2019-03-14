package com.java.summary.mybatis.plugins;

import com.java.summary.mybatis.annotation.AuthAop;
import com.java.summary.mybatis.reflection.AuthAopUtil;
import com.java.summary.mybatis.reflection.Reflector;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author Administrator
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class AuthInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object target = invocation.getTarget();
        if (target instanceof RoutingStatementHandler) {

            StatementHandler handler = (RoutingStatementHandler) target;
            StatementHandler delegate = (StatementHandler) Reflector.getPrivateField(handler, "delegate");

            MappedStatement mappedStatement = (MappedStatement) Reflector.getPrivateField(delegate, "mappedStatement");

            AuthAop authAop = AuthAopUtil.getAuthAop(mappedStatement);
            if (null == authAop) {
                System.out.println("未设置权限注解，直接放行。");
            } else {
                BoundSql boundSql = delegate.getBoundSql();
                String sql = boundSql.getSql();
//                System.out.println("sql : " + sql);
                System.out.println("通过拼接sql实现相应权限控制");
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
