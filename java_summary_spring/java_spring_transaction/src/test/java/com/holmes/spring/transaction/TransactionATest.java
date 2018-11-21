package com.holmes.spring.transaction;

import com.holmes.spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionATest extends BaseTest {

    @Autowired
    private TransactionA transactionA;

    @Test
    public void requiredTxATest() {
        transactionA.requiredTxA();
    }

    @Test
    public void requiredTxBTest() {
        transactionA.requiredTxB();
    }

    @Test
    public void requiredNewTxATest() {
        transactionA.requiredNewTxA();
    }

    @Test
    public void requiredNewTxBTest() {
        transactionA.requiredNewTxB();
    }

    @Test
    public void requiredNewTxCTest() {
        transactionA.requiredNewTxC();
    }

    @Test
    public void mandatoryTxATest() {
        transactionA.mandatoryTxA();
    }

    @Test
    public void mandatoryTxBTest() {
        transactionA.mandatoryTxB();
    }

    @Test
    public void supportsTxATest() {
        transactionA.supportsTxA();
    }

    @Test
    public void supportsTxBTest() {
        transactionA.supportsTxB();
    }

    @Test
    public void notSupportedTxATest() {
        transactionA.notSupportedTxA();
    }

    @Test
    public void notSupportedTxBTest() {
        transactionA.notSupportedTxB();
    }

    @Test
    public void neverTxATest() {
        transactionA.neverTxA();
    }

    @Test
    public void neverTxBTest() {
        transactionA.neverTxB();
    }

    @Test
    public void nestedTxATest() {
        transactionA.nestedTxA();
    }

    @Test
    public void nestedTxBTest() {
        transactionA.nestedTxB();
    }

    @Test
    public void nestedTxCTest() {
        transactionA.nestedTxC();
    }

}
