package com.java.staticc;

public class Test {

    @org.junit.Test
    public void test() {

        /**
         * Parent static block
         * ChildA static block
         * Parent block
         * Parent constructor
         * ChildA block
         * ChildA constructor
         */
        Parent child = new ChildA();
        /**
         * 1
         * 1
         * 2
         */
        System.out.println(child.a);
        System.out.println(((Parent) child).a);
        System.out.println(((ChildA) child).a);

        /**
         * Parent block
         * Parent constructor
         * ChildA block
         * ChildA constructor
         */
        ChildA childA = new ChildA();
        /**
         * 2
         * 1
         * 2
         */
        System.out.println(childA.a);
        System.out.println(((Parent) child).a);
        System.out.println(((ChildA) child).a);
    }
}
