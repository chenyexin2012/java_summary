package com.java.staticc;

public class Test {

    @org.junit.Test
    public void testChildA() {
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

    @org.junit.Test
    public void testChildB() {

        /**
         * Parent static block
         * ChildA static block
         * Parent block
         * Parent constructor
         * ChildA block
         * ChildA constructor
         * ChildB static block
         * Parent block
         * Parent constructor
         * ChildB constructor
         */
        ChildB childB = new ChildB();
    }

    @org.junit.Test
    public void testChildC() {

        /**
         * Parent static block
         * ChildC static block
         * Parent block
         * Parent constructor
         * ChildA static block
         * Parent block
         * Parent constructor
         * ChildA block
         * ChildA constructor
         * ChildC constructor
         */
        ChildC childC = new ChildC();
    }
}
