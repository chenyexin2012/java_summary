package com.holmes.learn.designPattern.abstractFactory;

import com.holmes.learn.designPattern.factory.MailSender;
import com.holmes.learn.designPattern.factory.Sender;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
