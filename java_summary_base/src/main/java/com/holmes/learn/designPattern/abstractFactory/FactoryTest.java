package com.holmes.learn.designPattern.abstractFactory;

import org.junit.Test;

import com.holmes.learn.designPattern.factory.Sender;

public class FactoryTest {

    @Test
    public void test() {

        SendSmsFactory sendSmsFactory = new SendSmsFactory();

        Sender sender = sendSmsFactory.produce();
        sender.Send();

        SendMailFactory sendMailFactory = new SendMailFactory();

        sender = sendMailFactory.produce();
        sender.Send();
    }
}
