package com.holmes.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Administrator
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * BeanPostProcessor
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] postProcessBeforeInitialization beanName = " + beanName);
        return bean;
    }

    /**
     * BeanPostProcessor
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] postProcessAfterInitialization beanName = " + beanName);
        return bean;
    }

}
