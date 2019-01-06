package com.holmes.template;

import org.junit.Test;

public class TemplateTest {

    @Test
    public void templateTest() {

        AbstractCar audi = new Audi("奥迪");

        AbstractCar benz = new Benz("奔驰");

        audi.working();

        benz.working();
    }
}
