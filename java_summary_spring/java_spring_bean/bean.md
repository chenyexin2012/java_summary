### bean的生命周期

实例化bean --> 为bean注入属性 --> 若有实现BeanNameAware接口，工厂会调用setBeanName方法将bean的id传入

--> 若有实现BeanFactoryAware接口，工厂会调用setBeanFactory方法将工厂传入

--> 若有BeanPostProcessor与bean关联，则会调用postProcessBeforeInitialization方法

--> 调用实现了的InitializingBean的afterPropertiesSet方法

--> 调用init-method方法

--> BeanPostProcessor.postProcessAfterInitialization

以上过程，bean实例已经创建成功

bean销毁过程会调用DisposableBean接口中的destroy方法和用户自定义的destroy-method方法
