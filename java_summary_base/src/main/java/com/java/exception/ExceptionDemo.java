package com.java.exception;

public class ExceptionDemo {

    public static void main(String[] args) {
        ExceptionDemo test = new ExceptionDemo();
        test.method1();
    }

    public ExceptionDemo() {
    }


    public void method1 () {
        try {
            method2();
        } catch (MyException e) {
            System.out.println("MyException...");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception...");
            e.printStackTrace();
        }
    }

    public void method2() throws MyException {
        try {
            throw new MyException("MyException test...");
        } catch (MyException e) {
            int i = 1 / 0;
            throw new MyException("MyException test...");
        }
    }
}
