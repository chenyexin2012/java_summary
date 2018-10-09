package com.holmes.learn.designPattern.factory;

/**
 * 通过在工厂类中建立多个方法来对实现同一接口的多个类按照各自
 * 需求进行实例的创建。可以防止在普通方法中因输入错误字符而导
 * 致对象创建失败。
 *
 * @author Administrator
 */
public class SendFactory1 {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}  