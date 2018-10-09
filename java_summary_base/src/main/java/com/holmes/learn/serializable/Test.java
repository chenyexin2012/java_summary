package com.holmes.learn.serializable;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address();
        address.setProvince("安徽省");
        address.setCity("合肥市");
        address.setStreet("九龙路");

        Student student = new Student();
        student.setAddress(address);
        student.setName("张三丰");
        student.setAge(118);


        Student student2 = student.clone();

        System.out.println(student);
        System.out.println(student2);
        System.out.println(student == student2);
        System.out.println(student.getAddress() == student2.getAddress());
    }
}
