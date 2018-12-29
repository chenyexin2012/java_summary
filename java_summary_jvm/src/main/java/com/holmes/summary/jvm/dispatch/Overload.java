package com.holmes.summary.jvm.dispatch;

import java.io.Serializable;

/**
 * @Description: 重载
 * @Author: holmes
 * @Version: 1.0.0
*/
public class Overload {

    public void method(byte a) {
        System.out.println("byte " + a);
    }

    public void method(Byte a) {
        System.out.println("Byte " + a);
    }

    public void method(short a) {
        System.out.println("short " + a);
    }

    public void method(Short a) {
        System.out.println("Short " + a);
    }

    public void method(int a) {
        System.out.println("int " + a);
    }

    public void method(Integer a) {
        System.out.println("Integer " + a);
    }

    public void method(long a) {
        System.out.println("long " + a);
    }

    public void method(Long a) {
        System.out.println("Long " + a);
    }

    public void method(float a) {
        System.out.println("float " + a);
    }

    public void method(Float a) {
        System.out.println("Float " + a);
    }

    public void method(double a) {
        System.out.println("double " + a);
    }

    public void method(Double a) {
        System.out.println("Double " + a);
    }

    public void method(char a) {
        System.out.println("char " + a);
    }

    public void method(Character a) {
        System.out.println("Character " + a);
    }

    public void method(boolean a) {
        System.out.println("boolean " + a);
    }

    public void method(Boolean a) {
        System.out.println("Boolean " + a);
    }

    public void method(Object a) {
        System.out.println("Object " + a);
    }

    public void method(Serializable a) {
        System.out.println("Serializable " + a);
    }

    public static void main(String[] args) {

        Overload overload = new Overload();

        overload.method(1);
    }
}
