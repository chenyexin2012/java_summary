package com.holmes.spring.transaction;

import com.holmes.spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionATest extends BaseTest {

    @Autowired
    private TransactionA transactionA;

    /**
     * REQUIRED运行在无事务环境中
     */
    @Test
    public void requiredTxATest() {
        transactionA.requiredTxA();
    }

    /**
     * REQUIRED运行在存在外部事务的环境中
     */
    @Test
    public void requiredTxBTest() {
        transactionA.requiredTxB();
    }

    /**
     * REQUIRES_NEW运行在无事务环境中
     */
    @Test
    public void requiredNewTxATest() {
        transactionA.requiredNewTxA();
    }

    /**
     * REQUIRES_NEW运行在存在外部事务的环境中
     */
    @Test
    public void requiredNewTxBTest() {
        transactionA.requiredNewTxB();
    }

    /**
     * REQUIRES_NEW运行在存在外部事务的环境中
     * 且内部事务出现异常
     */
    @Test
    public void requiredNewTxCTest() {
        transactionA.requiredNewTxC();
    }

    /**
     * MANDATORY运行在无事务环境中
     */
    @Test
    public void mandatoryTxATest() {
        transactionA.mandatoryTxA();
    }

    /**
     * MANDATORY运行在存在外部事务的环境中
     */
    @Test
    public void mandatoryTxBTest() {
        transactionA.mandatoryTxB();
    }

    /**
     * SUPPORTS运行在无事务环境中
     */
    @Test
    public void supportsTxATest() {
        transactionA.supportsTxA();
    }

    /**
     * SUPPORTS运行在存在外部事务的环境中
     */
    @Test
    public void supportsTxBTest() {
        transactionA.supportsTxB();
    }

    /**
     * NOT_SUPPORTED运行在无事务环境中
     */
    @Test
    public void notSupportedTxATest() {
        transactionA.notSupportedTxA();
    }

    /**
     * NOT_SUPPORTED运行在存在外部事务的环境中
     */
    @Test
    public void notSupportedTxBTest() {
        transactionA.notSupportedTxB();
    }

    /**
     * NEVER运行在无事务环境中
     */
    @Test
    public void neverTxATest() {
        transactionA.neverTxA();
    }

    /**
     * NEVER运行在存在外部事务的环境中
     */
    @Test
    public void neverTxBTest() {
        transactionA.neverTxB();
    }

    /**
     * NESTED运行在无事务环境中
     */
    @Test
    public void nestedTxATest() {
        transactionA.nestedTxA();
    }

    /**
     * NESTED运行在存在外部事务的环境中
     */
    @Test
    public void nestedTxBTest() {
        transactionA.nestedTxB();
    }

    /**
     * NESTED运行在存在外部事务的环境中
     * 且内部事务出现异常
     */
    @Test
    public void nestedTxCTest() {
        transactionA.nestedTxC();
    }

}
