package com.holmes.iterator;

public class ArrayList<T> implements List<T> {

    private Object[] array;

    private int length;

    private int capacity;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    @Override
    public T get(int index) {
        if (index >= length) {
            return null;
        }
        return (T) array[index];
    }

    @Override
    public void add(T t) {
        if (length > capacity) {
            return;
        }
        array[length] = t;
        length++;
    }

    @Override
    public void remove(T t) {
        for (int i = 0; i < length; i++) {
            if (t.equals(array[i])) {
                for (int j = i; j < length - 1; j++) {
                    array[j] = array[j + 1];
                }
                length--;
            }
        }
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }
}
