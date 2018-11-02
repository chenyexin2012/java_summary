package com.java.summary.reflection.base;

public class MyObject {

    public String name;
    public String phone;
    private String idCard;
    private String address = "北京市朝阳区";

    public MyObject() {
        System.out.println("默认构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private MyObject(String name, String idCard) {
        this.name = name;
        this.idCard = idCard;
        System.out.println("name:" + this.name + "\tidCard:" + this.idCard);
    }

    private void print() {
        System.out.println("name=" + name + ", phone=" + phone + ", idCard="
                + idCard + ", address=" + address);
    }

    @Override
    public String toString() {
        return "MyObject [name=" + name + ", phone=" + phone + ", idCard="
                + idCard + ", address=" + address + "]";
    }
}
