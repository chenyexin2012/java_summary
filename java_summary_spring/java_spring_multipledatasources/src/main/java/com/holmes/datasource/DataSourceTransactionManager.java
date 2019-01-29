package com.holmes.datasource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * 事务管理器
 *
 * @author Administrator
 */
public class DataSourceTransactionManager extends org.springframework.jdbc.datasource.DataSourceTransactionManager {

//    @Override
//    protected Object doGetTransaction() {
//
//        System.out.println(">>>>>>>>>>doGetTransaction");
//        return super.doGetTransaction();
//    }
//
//    @Override
//    protected boolean isExistingTransaction(Object transaction) {
//        System.out.println(">>>>>>>>>>isExistingTransaction");
//        return super.isExistingTransaction(transaction);
//    }
//
//    @Override
//    protected void doBegin(Object transaction, TransactionDefinition definition) {
//        System.out.println(">>>>>>>>>>doBegin");
//        super.doBegin(transaction, definition);
//    }
//
//    @Override
//    protected Object doSuspend(Object transaction) {
//        System.out.println(">>>>>>>>>>Object");
//        return super.doSuspend(transaction);
//    }
//
//    @Override
//    protected void doResume(Object transaction, Object suspendedResources) {
//        System.out.println(">>>>>>>>>>doResume");
//        super.doResume(transaction, suspendedResources);
//    }
//
//    @Override
//    protected void doCommit(DefaultTransactionStatus status) {
//        System.out.println(">>>>>>>>>>doCommit");
//        super.doCommit(status);
//    }
//
//    @Override
//    protected void doRollback(DefaultTransactionStatus status) {
//        System.out.println(">>>>>>>>>>doRollback");
//        super.doRollback(status);
//    }
//
//    @Override
//    protected void doCleanupAfterCompletion(Object transaction) {
//        System.out.println(">>>>>>>>>>doCleanupAfterCompletion");
//        super.doCleanupAfterCompletion(transaction);
//        //清理多数据源设置对象
//        DataSourceManager.clear();
//    }
//
//    @Override
//    protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition) throws SQLException {
//        System.out.println(">>>>>>>>>>prepareTransactionalConnection");
//        super.prepareTransactionalConnection(con, definition);
//    }
}