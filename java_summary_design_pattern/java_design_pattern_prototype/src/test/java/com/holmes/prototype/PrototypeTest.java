package com.holmes.prototype;

import org.junit.Test;

public class PrototypeTest {

    @Test
    public void shallowClonePrototype() throws CloneNotSupportedException {

        Address address = new Address();
        address.setProvince("安徽省");
        address.setCity("合肥市");
        address.setStreet("科学大道");

        ShallowCloneUser user = new ShallowCloneUser();
        user.setUserName("李三");
        user.setUserEmail("sdkfjasdfl@sina.com");
        user.setUserPassword("asdjflkadsjf");
        user.setAddress(address);

        ShallowCloneUser userClone = (ShallowCloneUser) user.clone();

        user.setUserName("李四");
        user.getAddress().setStreet("九龙路");

        System.out.println(user);
        System.out.println(userClone);
        System.out.println(user == userClone);
        System.out.println(user.getAddress() == userClone.getAddress());
    }

    @Test
    public void deepClonePrototypeTest() throws CloneNotSupportedException {

        Address address = new Address();
        address.setProvince("安徽省");
        address.setCity("合肥市");
        address.setStreet("科学大道");

        DeepCloneUser user = new DeepCloneUser();
        user.setUserName("李三");
        user.setUserEmail("sdkfjasdfl@sina.com");
        user.setUserPassword("asdjflkadsjf");
        user.setAddress(address);

        DeepCloneUser userClone = (DeepCloneUser) user.clone();

        user.setUserName("李四");
        user.getAddress().setStreet("九龙路");

        System.out.println(user);
        System.out.println(userClone);
        System.out.println(user == userClone);
        System.out.println(user.getAddress() == userClone.getAddress());
    }

    @Test
    public void prototypeManagerTest() throws Exception {

        Address address = new Address();
        address.setProvince("安徽省");
        address.setCity("合肥市");
        address.setStreet("科学大道");

        ShallowCloneUser shallowCloneUser = new ShallowCloneUser();
        shallowCloneUser.setUserName("李三");
        shallowCloneUser.setUserEmail("sdkfjasdfl@sina.com");
        shallowCloneUser.setUserPassword("asdjflkadsjf");
        shallowCloneUser.setAddress(address);

        DeepCloneUser deepCloneUser = new DeepCloneUser();
        deepCloneUser.setUserName("李三");
        deepCloneUser.setUserEmail("sdkfjasdfl@sina.com");
        deepCloneUser.setUserPassword("asdjflkadsjf");
        deepCloneUser.setAddress(address);

        PrototypeManager.setPrototype("shallowCloneUser", shallowCloneUser);
        PrototypeManager.setPrototype("deepCloneUser", deepCloneUser);


        User user1 = (User) PrototypeManager.getPrototype("shallowCloneUser");
        User user2 = (User) PrototypeManager.getPrototype("shallowCloneUser");

        User user3 = (User) PrototypeManager.getPrototype("deepCloneUser");
        User user4 = (User) PrototypeManager.getPrototype("deepCloneUser");

        System.out.println(user1 == user2);
        System.out.println(user1.getAddress() == user2.getAddress());

        System.out.println(user3 == user4);
        System.out.println(user3.getAddress() == user4.getAddress());
    }
}
