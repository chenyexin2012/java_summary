package org.apache.dubbo.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderStartWithConfig {

    public static void main(String[] args) throws Exception {

        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"classpath:spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read();
    }
}
