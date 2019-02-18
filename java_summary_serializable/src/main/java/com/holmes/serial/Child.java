package com.holmes.serial;

import java.io.Serializable;
import java.util.Date;

public class Child extends Parent implements Serializable {

    private String name;

    private Integer age;

    public Child() {
    }

    public Child(Integer id, String name, Date createTime, String name1, Integer age) {
        super(id, name, createTime);
        this.name = name1;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
