package com.holmes.iterator;

import org.junit.Test;

public class IteratorTest {

    @Test
    public void test() {

        List<String> list = new ArrayList<String>(20);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        list.remove("8");
        list.remove("3");

        System.out.println();
        System.out.println(list.get(5));
        System.out.println(list.size());
        System.out.println();

        iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
