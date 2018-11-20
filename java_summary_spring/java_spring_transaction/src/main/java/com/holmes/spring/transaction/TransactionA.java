package com.holmes.spring.transaction;

import com.holmes.spring.dao.UserDao;
import com.holmes.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionA {

    @Autowired
    private TransactionB transactionB;

    @Autowired
    private UserDao userDao;

    /**
     * 此处不开启事务，requiredTx方法中会开启一个事务
     * transactionB.requiredTx --> Propagation.REQUIRED
     */
    public void requiredTxA() {
        transactionB.requiredTx(new User("嬴渠梁", 10000));
    }

    /**
     * 此处开启一个事务，requiredTx方法会直接在此事务中运行
     * transactionB.requiredTx --> Propagation.REQUIRED
     */
    @Transactional
    public void requiredTxB() {
        transactionB.requiredTx(new User("嬴驷", 10000));
    }

    /**
     * 此处不开启事务，requiredNewTx方法中会开启一个事务
     * transactionB.requiredNewTx --> Propagation.REQUIRES_NEW
     */
    public void requiredNewTxA() {
        transactionB.requiredNewTx(new User("嬴荡", 10000));
    }

    /**
     * 此处开启一个事务，requiredNewTx方法中会开启一个新的事务，并将事务挂起
     * transactionB.requiredNewTx --> Propagation.REQUIRES_NEW
     */
    @Transactional
    public void requiredNewTxB() {
        transactionB.requiredNewTx(new User("嬴稷", 10000));
    }

    /**
     * 此处不开启事务，抛出异常
     * org.springframework.transaction.IllegalTransactionStateException:
     * No existing transaction found for transaction marked with propagation 'mandatory'
     * <p>
     * transactionB.mandatoryTx --> Propagation.MANDATORY
     */
    public void mandatoryTxA() {
        transactionB.mandatoryTx(new User("嬴柱", 10000));
    }

    /**
     * 此处开启一个事务，mandatoryTx会在此事务中运行
     * transactionB.mandatoryTx --> Propagation.MANDATORY
     */
    @Transactional
    public void mandatoryTxB() {
        transactionB.mandatoryTx(new User("嬴柱", 10000));
    }

    /**
     * 此处不开启事务，则supportsTx运行在无事务状态下，出现保存失败
     * transactionB.supportsTx --> Propagation.SUPPORTS
     */
    public void supportsTxA() {
        transactionB.supportsTx(new User("嬴异人", 10000));
    }

    /**
     * 此处开启事务，则supportsTx运行在此事务中
     * transactionB.supportsTx --> Propagation.SUPPORTS
     */
    @Transactional
    public void supportsTxB() {
        transactionB.supportsTx(new User("嬴异人", 10000));
    }

    /**
     * 此处不开启事务，正常运行
     * transactionB.notSupportedTx --> Propagation.NOT_SUPPORTED
     */
    public void notSupportedTxA() {
        transactionB.notSupportedTx(new User("嬴政", 10000));
    }

    /**
     * 此处开启事务，在notSupportedTx中会被挂起
     * transactionB.notSupportedTx --> Propagation.NOT_SUPPORTED
     */
    @Transactional
    public void notSupportedTxB() {
        transactionB.notSupportedTx(new User("嬴政", 10000));
    }

    /**
     * 此处不开启事务，正常运行
     * transactionB.neverTx --> Propagation.NEVER
     */
    public void neverTxA() {
        transactionB.neverTx(new User("嬴政", 10000));
    }

    /**
     * 此处开启事务，抛出异常
     * org.springframework.transaction.IllegalTransactionStateException:
     * Existing transaction found for transaction marked with propagation 'never'
     * <p>
     * transactionB.neverTx --> Propagation.NEVER
     */
    @Transactional
    public void neverTxB() {
        transactionB.neverTx(new User("嬴政", 10000));
    }

    /**
     * 此处不开启事务，nestedTx方法中会开启一个事务
     * transactionB.nestedTx --> Propagation.NESTED
     */
    public void nestedTxA() {
        transactionB.nestedTx(new User("商鞅", 10000));
    }

    /**
     * 此处开启事务，nestedTx方法中会开启一个事务，并单独提交或回滚
     * 需要在事务管理器中配置
     * <property name="nestedTransactionAllowed" value="true"/>
     * <p>
     * transactionB.nestedTx --> Propagation.NESTED
     */
    @Transactional
    public void nestedTxB() {
        transactionB.nestedTx(new User("张仪", 10000));
        transactionB.nestedTx(new User("张仪", 10000));
    }

}
