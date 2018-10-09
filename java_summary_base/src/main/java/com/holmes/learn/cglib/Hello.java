package com.holmes.learn.cglib;

public class Hello {

    private String message;

    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }

    public String sayHello(boolean throwException) throws Exception {
        System.out.println(this.message);
        if (throwException)
            throw new Exception("test exception");
        return "123";
    }

    public int add(int a, int b) {
        return a + b;
    }
}  