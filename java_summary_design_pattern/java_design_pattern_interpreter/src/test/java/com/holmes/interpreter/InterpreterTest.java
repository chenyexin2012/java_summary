package com.holmes.interpreter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InterpreterTest {

    @Test
    public void interpreterTest() {

        List<String> list = new ArrayList<>(9);

        list.add("10");
        list.add("2");
        list.add("+");
        list.add("3");
        list.add("2");
        list.add("-");
        list.add("*");
        list.add("2");
        list.add("/");

        Context context = new Context(list);

        System.out.println(context.calculator());
    }
}
