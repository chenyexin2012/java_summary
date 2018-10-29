package com.java.summary.mybatis;

import com.java.summary.mybatis.dao.UserDao;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * mybatis的一级缓存测试
 * 测试前先关闭对应的二级缓存
 */
public class LocalCacheTest extends BaseTest{

    /**
     * <setting name="localCacheScope" value="SESSION" />
     * 或
     * <setting name="localCacheScope" value="STATEMENT" />
     *
     * 同一个sql执行两次相同的查询，配置为SESSION时只执行一次sql，为STATEMENT则执行两次
     */
    @Test
    public void testCache1() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(1);
        System.out.println(user);
        user = userDao.selectUserById(1);
        System.out.println(user);

        session.close();
    }

    /**
     * <setting name="localCacheScope" value="SESSION" />
     * 同一个sql执行两次不同的查询，执行两次sql
     */
    @Test
    public void testCache2() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(1);
        System.out.println(user);
        user = userDao.selectUserById(2);
        System.out.println(user);

        session.close();
    }

    /**
     * <setting name="localCacheScope" value="SESSION" />
     * 不同的session中，同一个sql执行相同的查询，执行两次sql
     */
    @Test
    public void testCache3() {

        SqlSession session1 = sessionFactory.openSession();
        UserDao userDao = session1.getMapper(UserDao.class);
        User user = userDao.selectUserById(1);
        System.out.println(user);

        SqlSession session2 = sessionFactory.openSession();
        userDao = session2.getMapper(UserDao.class);
        user = userDao.selectUserById(1);
        System.out.println(user);

        session1.close();
        session2.close();
    }

    /**
     * <setting name="localCacheScope" value="SESSION" />
     * 同一个session中，在执行一次查询之后，执行增删改操作，会清除缓存，
     * 再次查询时仍会执行sql
     */
    @Test
    public void testCache4() {

        SqlSession session = sessionFactory.openSession(false);
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(1);
        System.out.println(user);

        // 执行一次更新操作
        user.setUserEmail("wwwsfsdf@123.com");
        userDao.update(user);
        // 执行一次新增操作
//        user = new User();
//        user.setUserName("sherlock");
//        user.setUserPassword("123456");
//        user.setUserEmail("xxx@xxx.com");
//        userDao.insert(user);

        // 执行一次删除操作
//        userDao.deleteById(2);

        user = userDao.selectUserById(1);
        System.out.println(user);

        session.commit();
        session.close();
    }

}
