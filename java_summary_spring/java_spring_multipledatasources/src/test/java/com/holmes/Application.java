package com.holmes;

import com.holmes.datasource.DataSourceManager;
import com.holmes.enums.DataSourceType;
import com.holmes.pojo.User;
import com.holmes.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-config.xml")
public class Application {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserService userService;

    @Test
    public void test() {
        System.out.println(userService);
    }

    @Test
    public void selectList() {

        Map<String, Object> paramMap = new HashMap<>();
        List<User> userList = userService.selectList(paramMap, DataSourceType.DATA_SOURCE_A);
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        userList = userService.selectList(paramMap, DataSourceType.DATA_SOURCE_B);
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        userList = userService.selectList(paramMap, DataSourceType.DATA_SOURCE_C);
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUserName("holmes");
        user.setUserPassword("123456");
        user.setUserEmail("www.baidu.com");
        userService.insert(user);
    }

    @Test
    public void deleteById() {
//        userService.deleteById(userId);
    }

    @Test
    public void insertList() {

        DataSourceManager.set(DataSourceType.DATA_SOURCE_B);

        List<User> list = new LinkedList<>();
        User user = new User();
        user.setUserName("holmes");
        user.setUserPassword("123456");
        user.setUserEmail("www.baidu.com");
        list.add(user);

        userService.insertList(list);
    }

    @Test
    public void update() {
//        userService.update(user);
    }

    @Test
    public void copyData() {
        userService.copyData();
    }
}
