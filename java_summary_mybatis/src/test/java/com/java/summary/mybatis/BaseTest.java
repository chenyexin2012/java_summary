package com.java.summary.mybatis;

import com.java.summary.mybatis.dao.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;

public class BaseTest {

    protected SqlSessionFactory sessionFactory = null;

    @Before
    public void before() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mybatis/mybatis-config.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @After
    public void after() {
    }
}
