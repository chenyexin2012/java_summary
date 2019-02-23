package com.holmes.examination;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * final 常见笔试面试要点
 * <p>
 * final可以修饰成员变量、本地变量、方法以及类
 *
 * @author Administrator
 */
public class FinalTest {

    public static void main(String[] args) {

        final Integer finalInt = new Integer(10);
        // 不能对final变量再次赋值
//        finalInt = finalInt + 1;

        final AtomicInteger finalAtomicInt = new AtomicInteger(10);
        finalAtomicInt.getAndIncrement();
    }

    final class FinalClass {
    }

    class TestClass {

        /**
         * final成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误。
         */
//        private final Integer finalInt = null;

        private final Integer finalInt;

        private final String finalStr = "hello";

        TestClass() {
            finalInt = new Integer(10);
        }

        public final void finalMethod() {
        }

        public void finalMethod(int a) {
        }

        public final void finalMethod(String str) {
        }
    }

    public class TestClassChild extends TestClass {

        // final 修饰的方法不能被覆盖
//        public void finalMethod() {
//        }
    }

    // final 不能用来修饰 abstract 类
//    final abstract class AbstractClass {
//    }

    // 不能继承使用 final 修饰的类
//    class FinalClassChild extends FinalClass {
//    }
}
