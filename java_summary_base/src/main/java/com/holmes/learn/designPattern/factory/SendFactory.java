package com.holmes.learn.designPattern.factory;

/**
 * 普通工厂模式
 * 通过对实现统一接口的多个类按照各自需求进行实例创建
 *
 * @author Administrator
 */
public class SendFactory {

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}