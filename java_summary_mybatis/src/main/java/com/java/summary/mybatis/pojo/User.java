package com.java.summary.mybatis.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {


    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Address address;
    private List<Order> orders;


    public User() {
    }

    public User(String userName, String userPassword, String userEmail, Address address, List<Order> orders) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.address = address;
        this.orders = orders;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}