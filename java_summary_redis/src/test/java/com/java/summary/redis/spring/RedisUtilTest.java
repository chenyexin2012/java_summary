package com.java.summary.redis.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

public class RedisUtilTest {

    private ClassPathXmlApplicationContext applicationContext = null;
    private RedisUtil redisUtil = null;
    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        applicationContext.start();
        redisUtil = applicationContext.getBean("redisUtil", RedisUtil.class);
    }

    @Test
    public void testList() {

        List<Object> list = new LinkedList<Object>();
        for(int i = 0; i < 100; i++) {
            list.add(String.valueOf(System.currentTimeMillis()));
        }
        System.out.println(redisUtil.lLpushAll("list", list));
    }

    @After
    public void after() {
        applicationContext.close();
    }
}
