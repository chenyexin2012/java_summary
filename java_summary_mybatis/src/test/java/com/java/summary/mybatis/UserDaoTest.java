package com.java.summary.mybatis;

import com.java.summary.mybatis.dao.UserDao;
import com.java.summary.mybatis.plugins.Page;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class UserDaoTest extends BaseTest{

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

        Map<String, Object> param = new HashMap<>();
//        param.put("userId", 1);
//        param.put("userName", "h");
//        param.put("page", new Page(1, 10));

        List<User> list = userDao.selectList(param);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectList1() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        Map<String, Object> param = new HashMap<>();
        param.put("userId", 1);
        param.put("userName", "h");
        param.put("page", new Page(1, 10));

        List<User> list = userDao.selectList1(param);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectList2() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        Map<String, Object> param = new HashMap<>();
        param.put("userId", 1);
        param.put("userName", "h");
        param.put("page", new Page(1, 10));

        List<User> list = userDao.selectList2(param);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectList3() {

        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);

        Map<String, Object> param = new HashMap<>();
        param.put("userId", 1);
        param.put("userName", "h");
        param.put("page", new Page(1, 10));

        List<User> list = userDao.selectList3(param);
        for (User user : list) {
            System.out.println(user);
        }
    }



    @Test
    public void testUpdate() {
        SqlSession session = sessionFactory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(10);
        if(null != user) {
            user.setUserName("holmes");
            userDao.update(user);
        }
    }

    @Test
    public void testUpdate1() {
        SqlSession session = sessionFactory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(11);
        if(null != user) {
            user.setUserName("holmes");
            userDao.update1(user);
        }
    }

    @Test
    public void testUpdate2() {
        SqlSession session = sessionFactory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);

        User user = userDao.selectUserById(21);
        if(null != user) {
            user.setUserName("holmes");
            userDao.update2(user);
        }
    }


    @Test
    public void testDeleteByIdList() {

        SqlSession session = sessionFactory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);

        List<Integer> list = new LinkedList<Integer>(){
            {
                add(1);
                add(2);
                add(3);
            }
        };
        userDao.deleteByIdList(list);
    }

    @Test
    public void testDeleteByIdArray() {

        SqlSession session = sessionFactory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);
        Integer ids[] = {1,2,3};
        userDao.deleteByIdArray(ids);
    }
}
