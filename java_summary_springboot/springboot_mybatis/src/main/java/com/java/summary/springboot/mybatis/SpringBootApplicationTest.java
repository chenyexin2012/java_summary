package com.java.summary.springboot.mybatis;

import com.java.summary.springboot.mybatis.entity.User;
import com.java.summary.springboot.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@ActiveProfiles(value = "dev")
public class SpringBootApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUserById() {
        System.out.println(userMapper.selectUserById(1));
    }

    @Test
    public void testInsert() {

        List<User> userList = new ArrayList<User>(10);
        User user = null;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setUserName("sherlock");
            user.setUserPassword("123456");
            user.setUserEmail("xxx@xxx.com");
            userList.add(user);
        }
        userMapper.insertList(userList);
    }

    @Test
    public void testSelectList() {
        List<User> list = userMapper.selectList();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
