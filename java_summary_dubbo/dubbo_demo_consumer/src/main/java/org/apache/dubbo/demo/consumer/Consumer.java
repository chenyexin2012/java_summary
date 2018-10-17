package org.apache.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.apache.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
//        System.setProperty("java.net.preferIPv4Stack", "true");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
//        context.start();
//        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy
//
//        try {
//            Class interfaceClass = Class.forName(DemoService.class.getName(), true, Thread.currentThread()
//                    .getContextClassLoader());
//
//            System.out.println(interfaceClass);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // 使用API方式
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("test-dubbo-consumer1"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        referenceConfig.setInterface(DemoService.class);
//        referenceConfig.setUrl("dubbo://127.0.0.1:16666");
        DemoService demoService = referenceConfig.get();

        // org.apache.dubbo.common.bytecode.proxy0@3349e9bb
        System.out.println(demoService);
        while (true) {
            try {
                Thread.sleep(1000);
                String hello = demoService.sayHello("world"); // call remote method
                System.out.println(hello); // get result

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
