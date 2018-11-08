package com.holmes.decorator;

import org.junit.Test;

public class DecoratorTest {

    @Test
    public void test() {

        Window window = new Window();
        WindowDecorator decorator = new WindowDecorator(window);
        decorator.display();
    }
}
