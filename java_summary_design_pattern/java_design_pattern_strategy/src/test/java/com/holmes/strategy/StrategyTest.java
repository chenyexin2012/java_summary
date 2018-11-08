package com.holmes.strategy;

import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {

        Integer[] array = new Integer[]{1, 2, 3};
        // 使用快速排序
        Context context = new Context(new QuickSort());
        display(context.sort(array));
        // 切换归并排序
        context.setSortStrategy(new MergeSort());
        display(context.sort(array));
        // 切换冒泡排序
        context.setSortStrategy(new BubbleSort());
        display(context.sort(array));
    }

    private void display(Object[] objects) {
        for (Object object : objects) {
            System.out.print(object + "\t");
        }
        System.out.println();
    }
}
