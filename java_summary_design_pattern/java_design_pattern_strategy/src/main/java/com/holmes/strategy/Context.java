package com.holmes.strategy;

/**
 * 具体使用环境
 *
 * @param <T>
 */
public class Context<T extends Comparable<T>> {

    private SortStrategy<T> sortStrategy;

    /**
     * 构造函数，传入指定的排序策略
     *
     * @param sortStrategy
     */
    public Context(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    /**
     * 指定排序策略
     *
     * @param sortStrategy
     */
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    /**
     * 具体的调用方法
     *
     * @param array
     * @return
     */
    public T[] sort(T[] array) {
        return this.sortStrategy.sort(array);
    }
}
