package com.holmes.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanFactoryPostProcessor] postProcessBeanFactory ...");

        System.out.println("studentA ConstructorArgumentValues");
        BeanDefinition studentA = beanFactory.getBeanDefinition("studentA");
        ConstructorArgumentValues constructorArgumentValues = studentA.getConstructorArgumentValues();
        Map<Integer, ConstructorArgumentValues.ValueHolder> map = constructorArgumentValues.getIndexedArgumentValues();
        for(Map.Entry<Integer, ConstructorArgumentValues.ValueHolder> entry : map.entrySet()) {
            System.out.printf("index = %d, value = %s\n", entry.getKey(), entry.getValue().getValue());
            // 修改name
            if(entry.getKey() == 1) {
                entry.getValue().setValue("王五");
            }
        }

        System.out.println("studentB MutablePropertyValues");
        BeanDefinition studentB = beanFactory.getBeanDefinition("studentB");
        MutablePropertyValues mutablePropertyValues = studentB.getPropertyValues();
        List<PropertyValue> list = mutablePropertyValues.getPropertyValueList();
        for(PropertyValue propertyValue : list) {
            System.out.printf("propertyName = %s, propertyValue = %s\n", propertyValue.getName(), propertyValue.getValue());
            // 修改name
            if("name".equals(propertyValue.getName())) {
                propertyValue.setConvertedValue("赵六");
            }
        }
    }
}
