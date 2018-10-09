package com.holmes.learn.designPattern.abstractFactory;

import com.holmes.learn.designPattern.factory.Sender;

public interface Provider {

    public Sender produce();
}
