package com.holmes.spring.dao;

import com.holmes.spring.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
//@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getUserById(Long id) {
        User user = getCurrentSession().find(User.class, id);
        return user;
    }

    public List<User> selectList() {
        Query query = getCurrentSession().createQuery("from User");
        List<User> list = query.list();
        return list;
    }

    public void addUser(User user) {
        getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        getCurrentSession().update(user);
    }
}
