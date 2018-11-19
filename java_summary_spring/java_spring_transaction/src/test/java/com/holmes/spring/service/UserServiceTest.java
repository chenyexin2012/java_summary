package com.holmes.spring.service;

import com.holmes.spring.BaseTest;
import com.holmes.spring.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser(new User("张三", 10000));
    }
}
