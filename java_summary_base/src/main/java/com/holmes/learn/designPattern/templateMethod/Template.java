package com.holmes.learn.designPattern.templateMethod;

public abstract class Template {

    public void template() {
        stepA();
        stepB();
        stepC();
        stepD();
    }

    private void stepA() {
        System.out.println("stepA...");
    }

    protected abstract void stepB();

    private void stepC() {
        System.out.println("stepA...");
    }

    protected abstract void stepD();
}
