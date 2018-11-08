package com.holmes.strategy;

/**
 * 策略的具体实现
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public T[] sort(T[] array) {
        System.out.println("MergeSort...");
        return array;
    }
}
