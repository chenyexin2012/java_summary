package org.apache.dubbo.demo.consumer;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.api.IService;
import org.apache.dubbo.exception.NullInputException;

public class ConsumerStartWithAPI {

    public static void main(String[] args) throws NullInputException, InterruptedException {
        // 使用API方式
        ReferenceConfig<IService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("test-dubbo-consumer1"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        referenceConfig.setInterface(IService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("helloService");
        referenceConfig.setId("helloService");
//        referenceConfig.setUrl("dubbo://127.0.0.1:16666");
        IService helloService = referenceConfig.get();

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
