package com.holmes.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author Administrator
 */
public class Student implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    public Student() {
        System.out.println("\n[Student] construction ...");
    }

    public Student(Integer id, String name, Integer age, String address) {
        System.out.println("\n[Student] construction ...");
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * 初始化方法 对应配置 init-method
     */
    public void initMethod() {
        System.out.println("[Student] init-method execute...");
    }

    /**
     * 销毁方法 对应配置 destroy-method
     */
    public void destroyMethod() {
        System.out.println("[Student] destroy-method execute...");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("[Student] set id = " + id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("[Student] set name = " + name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("[Student] set age = " + age);
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("[Student] set address = " + address);
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * BeanFactoryAware
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanFactoryAware] setBeanFactory ...");
    }

    /**
     * BeanNameAware
     *
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("[BeanNameAware] setBeanName name = " + name);
    }

    /**
     * InitializingBean
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean] afterPropertiesSet ...");
    }

    /**
     * DisposableBean
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("[DisposableBean] destroy ...");
    }
}
