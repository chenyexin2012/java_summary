package org.apache.dubbo.demo.provider;

import org.apache.dubbo.api.IService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.demo.provider.service.HelloService;

public class ProviderStartWithAPI {

    public static void main(String[] args) throws Exception {
        // 使用API方式
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-provider");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://localhost:2181");
//        registryConfig.setRegister(false);
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", 20881);

        ServiceConfig<IService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setId("helloService");
        serviceConfig.setGroup("helloService");
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(IService.class);
        serviceConfig.setRef(new HelloService());
        serviceConfig.export();

        System.in.read();
    }

}
