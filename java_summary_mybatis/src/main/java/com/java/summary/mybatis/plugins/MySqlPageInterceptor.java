package com.java.summary.mybatis.plugins;

import com.java.summary.mybatis.reflection.Reflector;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

/**
 * 一个简单的mysql分页插件
 *
 * @author Administrator
 */
@Intercepts(@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}))
public class MySqlPageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object target = invocation.getTarget();
        if (target instanceof RoutingStatementHandler) {
            StatementHandler statementHandler = (RoutingStatementHandler) target;
            StatementHandler handler = (StatementHandler) Reflector.getPrivateField(statementHandler, "delegate");

            BoundSql boundSql = handler.getBoundSql();
            Object object = Reflector.getPrivateField(boundSql, "parameterObject");
            Page page = null;
            // 分页
            if (object instanceof Page) {
                page = (Page) object;
            } else if (object instanceof HashMap) {
                HashMap paramMap = (HashMap) object;
                // 参数注解命名方式 @Param("page")
                try {
                    Object pageObj = paramMap.get("page");
                    if (null != pageObj && pageObj instanceof Page) {
                        page = (Page) pageObj;
                    }
                } catch (BindingException e) {
                }
            }
            if (null != page) {
                StringBuilder sql = new StringBuilder(boundSql.getSql());
                // 追加分页语句
                sql.append(" limit ").append(page.getOffset()).append(",").append(page.getLimit());
                Reflector.setPrivateField(boundSql, "sql", sql.toString());
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
