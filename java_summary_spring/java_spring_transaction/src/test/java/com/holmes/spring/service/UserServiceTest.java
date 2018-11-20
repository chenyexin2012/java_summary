package com.holmes.spring.service;

import com.holmes.spring.BaseTest;
import com.holmes.spring.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUserTest() {
        userService.addUser(new User("王五", 1000));
    }
    @Test
    public void getUserByIdTest() {

        System.out.println(userService.getUserById(1L));
    }

    @Test
    public void selectListTest() {
        System.out.println(userService.selectList(new User()));
    }

    @Test
    public void updateUserTest() {

    }

    @Test
    public void transferTest() {

        User user1 = userService.getUserById(1L);
        User user2 = userService.getUserById(10L);

        userService.transfer(user1, user2, 100);
    }
}
