package com.holmes.learn.designPattern.iterator;

public class MyIterator implements Iterator {

    private Collection collection;

    private int pos = -1;

    public MyIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        pos--;
        return collection.get(pos);
    }

    @Override
    public Object next() {
        pos++;
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        if (pos >= -1 && pos < collection.size() - 1)
            return true;
        return false;
    }

    @Override
    public Object first() {
        return collection.get(0);
    }

}
