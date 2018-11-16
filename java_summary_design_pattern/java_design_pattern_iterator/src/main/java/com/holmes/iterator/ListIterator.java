package com.holmes.iterator;

public class ListIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index;

    public ListIterator(List<T> list) {
        this.list = list;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        T t = list.get(index);
        index++;
        return t;
    }
}
