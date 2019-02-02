package com.holmes.mybatis;

import com.holmes.mybatis.pojo.User;
import com.holmes.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-config.xml")
public class Application {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Test
    public void selectList() {

        List<User> userList = userService.selectList(new User());

        System.out.println(userList);
    }
}
