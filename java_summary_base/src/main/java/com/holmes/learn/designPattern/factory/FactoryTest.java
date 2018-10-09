package com.holmes.learn.designPattern.factory;

import org.junit.Test;

public class FactoryTest {

    @Test
    public void test() {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.Send();

        sender = factory.produce("mail");
        sender.Send();

        sender = factory.produce("ddd");
        sender.Send();
    }

    @Test
    public void test2() {
        SendFactory1 factory = new SendFactory1();

        Sender sender = factory.produceMail();
        sender.Send();

        sender = factory.produceSms();
        sender.Send();
    }

    @Test
    public void test3() {

        Sender sender = SendFactory2.produceMail();
        sender.Send();

        sender = SendFactory2.produceSms();
        sender.Send();
    }
}  