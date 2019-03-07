package com.holmes.examination;

/**
 * @author Administrator
 */
public class InnerStaticClassTest {

    public static void main(String[] args) {

        InnerStaticClassTest innerStaticClassTest = new InnerStaticClassTest();
        System.out.println(new InnerClass().str);
    }

    InnerStaticClassTest() {
        System.out.println("InnerStaticClassTest constructor ...");
    }

    private static class InnerClass {

        public String str = "Hello World";

        public InnerStaticClassTest innerStaticClass = new InnerStaticClassTest();

        public static InnerStaticClassTest staticClass = new InnerStaticClassTest();

        {
            System.out.println("inner block ...");
        }

        static {
            System.out.println("inner static block ...");
        }

        public InnerClass() {
            System.out.println("InnerClass constructor ...");
        }
    }
}
