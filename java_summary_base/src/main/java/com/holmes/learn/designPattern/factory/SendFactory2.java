package com.holmes.learn.designPattern.factory;

/**
 * @author Administrator
 */
public class SendFactory2 {

    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}