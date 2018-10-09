package com.java.summary.mybatis.pojo;

import java.io.Serializable;

public class Order implements Serializable {

    private Integer orderId;
    private Integer userId;
    private String name;
    private Integer count;

    public Order(Integer userId, String name, Integer count) {
        this.userId = userId;
        this.name = name;
        this.count = count;
    }

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
