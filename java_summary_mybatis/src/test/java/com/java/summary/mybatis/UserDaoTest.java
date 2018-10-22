package com.java.summary.mybatis;

import com.java.summary.mybatis.dao.UserDao;
import com.java.summary.mybatis.plugins.Page;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

    private SqlSessionFactory sessionFactory = null;

    @Before
    public void before() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mybatis/mybatis-config.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void after() {
    }

    @org.junit.Test
    public void testInsert() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        List<User> userList = new ArrayList<User>(10);
        User user = null;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setUserName("sherlock");
            user.setUserPassword("12345678");
            user.setUserEmail("xxx@xxx.com");
            userList.add(user);
        }
        userDao.insertList(userList);

        session.commit();
    }

    @Test
    public void testSelectList() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        List<User> list = userDao.selectList(new Page(0, 10));
        for (User user : list) {
            System.out.println(user);
        }
    }
}
