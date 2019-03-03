package com.holmes.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author Administrator
 */
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter] postProcessBeforeInitialization beanName = " + beanName);
        return null;
    }
    /**
     * 实例化Bean之后
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter] postProcessAfterInitialization beanName = " + beanName);
        return bean;
    }


    /**
     * 设置某个属性时调用
     *
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter] postProcessPropertyValues beanName = " + beanName);
        return pvs;
    }
}