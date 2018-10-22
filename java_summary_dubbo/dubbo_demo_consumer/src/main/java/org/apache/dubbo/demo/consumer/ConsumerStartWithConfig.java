package org.apache.dubbo.demo.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.api.IService;
import org.apache.dubbo.exception.NullInputException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerStartWithConfig {

    public static void main(String[] args) throws NullInputException, InterruptedException {

        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/dubbo-demo-consumer.xml"});
        context.start();
        IService helloService = (IService) context.getBean("helloService"); // get remote service proxy

        JSONObject paramObj = new JSONObject();
        paramObj.put("message", "hello");

        for(int i = 0; i < 1000; i++) {
            JSONObject resultObj = helloService.handle(paramObj);
            String message = resultObj.getString("message");
            System.out.println(message);

            Thread.sleep(1000);
        }
        try {
            Class interfaceClass = Class.forName(IService.class.getName(), true, Thread.currentThread()
                    .getContextClassLoader());

            System.out.println(interfaceClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
