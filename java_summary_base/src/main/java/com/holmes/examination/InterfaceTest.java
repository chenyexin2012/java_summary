package com.holmes.examination;


/**
 * Interface 常见笔试面试要点
 *
 * @author Administrator
 */
public class InterfaceTest {


    interface Interface {

        // 接口中可以定义静态变量
//        String str;
        static String str1 = "hello";
        final static String str2 = "hello";
        
        // 不能用protected修改接口中的方法
//    protected void protectedMethod();

        // 不能用private修改接口中的方法
//    private void privateMethod();

        // 不能用final修改接口中的方法
//    public final void finalMethod();

        // 不能在接口中实现非default或static修饰的方法
//    public void concreteMethod() {
//    }

        public void method();

        static void staticMethod() {
            System.out.println("this is a static method!");
        }

        default void defaultMethod() {
            System.out.println("this is a default method!");
        }
    }

    class Concrete implements Interface {

        @Override
        public void method() {

        }

//        /**
//         * 实现类可以重写接口中的default方法
//         */
//        @Override
//        public void defaultMethod() {
//
//        }
    }

}
