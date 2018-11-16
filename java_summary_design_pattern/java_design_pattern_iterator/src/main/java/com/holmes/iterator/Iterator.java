package com.holmes.iterator;

/**
 * 迭代器接口
 *
 * @param <T>
 */
public interface Iterator<T> {

    public boolean hasNext();

    public T next();
}
