package com.holmes.learn.designPattern.iterator;

public class MyCollection implements Collection {

    private String[] str = {"A", "B", "C", "D", "E"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {

        if (i < 0 || i >= str.length)
            throw new StringIndexOutOfBoundsException(i);
        return str[i];
    }

    @Override
    public int size() {
        return str.length;
    }

}
