package com.holmes.learn.designPattern.abstractFactory;

import com.holmes.learn.designPattern.factory.Sender;
import com.holmes.learn.designPattern.factory.SmsSender;

public class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }

}
