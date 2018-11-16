package com.holmes.iterator;

/**
 * List接口
 */
public interface List<T> {

    public T get(int index);

    public void add(T t);

    public void remove(T t);

    public int size();

    public Iterator<T> iterator();
}
