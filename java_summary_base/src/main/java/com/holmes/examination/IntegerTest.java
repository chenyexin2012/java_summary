package com.holmes.examination;

/**
 * Integer 常见笔试面试要点
 *
 * @author Administrator
 */
public class IntegerTest {

    public static void main(String[] args) {

        Integer a = 1;

        Integer b = 2;

        Integer c = 3;

        Integer d = 3;

        Integer e = 128;

        Integer f = 128;

        Long g = 3L;

        System.out.println(c == d);

        System.out.println(c == (a + b));

        System.out.println(c.equals(a + b));

        System.out.println(e == f);

        System.out.println(g == (a + b));

        System.out.println(g.equals(a + b));
    }
}
