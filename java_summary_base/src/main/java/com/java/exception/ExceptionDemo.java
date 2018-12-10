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
            method3();
        } catch (MyException e) {
            System.out.println("MyException...");
            e.printStackTrace();
        } catch (MyException1 e) {
            System.out.println("MyException1...");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception...");
            e.printStackTrace();
        }
    }

    public void method2() throws MyException {
        throw new MyException("MyException test...");
    }

    public void method3() throws MyException1 {
        throw new MyException1("MyException1 test...");
    }
}
