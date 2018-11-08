package com.holmes.strategy;

/**
 * 策略的抽象
 *
 * @param <T>
 */
public interface SortStrategy<T extends Comparable<T>> {

    public T[] sort(T[] array);
}
