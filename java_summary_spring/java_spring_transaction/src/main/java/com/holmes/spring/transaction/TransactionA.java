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
        transactionB.requiredNewTxA(new User("嬴荡", 10000));
    }

    /**
     * 此处开启一个事务，requiredNewTx方法中会开启一个新的事务，并将事务挂起。
     *
     * transactionB.requiredNewTx --> Propagation.REQUIRES_NEW
     */
    @Transactional
    public void requiredNewTxB() {
        userDao.addUser(new User("嬴荡", 10000));

        // requiredNewTxA方法中的事务不依赖与外部事务
        // 会单独的提交
        transactionB.requiredNewTxA(new User("嬴稷", 10000));
        userDao.addUser(new User("嬴柱", 10000));
    }

    /**
     * 测试requiredNewTxB中事务回滚
     */
    @Transactional
    public void requiredNewTxC() {
        userDao.addUser(new User("嬴稷", 10000));
        // requiredNewTxB中出现异常
        transactionB.requiredNewTxB(new User("范睢", 10000));
        userDao.addUser(new User("司马错", 10000));
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
        transactionB.nestedTxA(new User("商鞅", 10000));
    }

    /**
     * 此处开启事务，nestedTx方法中会在嵌套事务中运行
     * 且嵌套事务在外部事务结束时提交
     * transactionB.nestedTx --> Propagation.NESTED
     */
    @Transactional
    public void nestedTxB() {
        userDao.addUser(new User("苏秦", 10000));
        transactionB.nestedTxA(new User("张仪", 10000));
        userDao.addUser(new User("苏代", 10000));
    }

    /**
     *
     */
    @Transactional
    public void nestedTxC() {
        userDao.addUser(new User("白起", 10000));
        // nestedTxB中出现异常
        // 在nestedTxB方法执行前，会生成一个保存点，保存当前的状态，
        // 若nestedTxB执行失败，则会恢复到之前保存的状态。
        transactionB.nestedTxB(new User("魏冉", 10000));
        userDao.addUser(new User("芈戎", 10000));
    }

}
