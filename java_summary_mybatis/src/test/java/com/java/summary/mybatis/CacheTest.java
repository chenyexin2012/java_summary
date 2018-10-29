package com.java.summary.mybatis;

import com.java.summary.mybatis.dao.UserDao;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 二级缓存测试
 */
public class CacheTest extends BaseTest{

    /**
     * 不同的session中，同一个sql执行相同的查询，只执行一次sql
     *
     * 二级缓存生效的条件：SqlSession已经关闭或提交
     *
     * 在LocalCacheTest的testCache方法中，即使开启了二级缓存，但由于session1在session2执行查询前
     * 没有进行提交或者关闭动作，因此缓存不会生效，sql还是会执行两次。
     * @see LocalCacheTest#testCache3()
     */
    @Test
    public void test1() {

        SqlSession session1 = sessionFactory.openSession();
        UserDao userDao = session1.getMapper(UserDao.class);
        User user = userDao.selectUserById(1);
        System.out.println(user);
        // 提交，缓存才会生效
        session1.commit();

        SqlSession session2 = sessionFactory.openSession();
        userDao = session2.getMapper(UserDao.class);
        user = userDao.selectUserById(1);
        System.out.println(user);

        session1.close();
        session2.close();
    }
}
