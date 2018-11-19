package com.holmes.spring.dao;

import com.holmes.spring.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.update(user);
        session.close();
    }
}
