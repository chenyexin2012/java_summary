package com.java.exception;

import org.junit.Assert;

public class Test {

    private int getValue1() {

        int a = 0;
        try {
            a = 1;
            return a;
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a = 3;
        }
    }

    private int getValue2() {

        int a = 0;
        try {
            a = 1 / 0;
            return a;
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a = 3;
        }
    }

    private int getValue3() {

        int a = 0;
        try {
            a = 1 / 0;
            return a;
        } catch (Exception e) {
            a = 2;
        } finally {
            a = 3;
        }
        return a;
    }

    @org.junit.Test
    public void test() {

        Assert.assertEquals(getValue1(), 1);

        Assert.assertEquals(getValue2(), 2);

        Assert.assertEquals(getValue3(), 3);
    }
}
