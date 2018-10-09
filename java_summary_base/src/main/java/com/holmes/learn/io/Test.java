package com.holmes.learn.io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("");

        FileReader fileReader = new FileReader("");

        //采用适配器模式
        InputStreamReader isr = null;

        //采用装饰器模式
        FilterInputStream fiis = null;
    }
}
