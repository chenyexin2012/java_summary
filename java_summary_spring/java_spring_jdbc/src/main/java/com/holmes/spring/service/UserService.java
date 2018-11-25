package com.holmes.spring.service;

import com.holmes.spring.dao.UserDao;
import com.holmes.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> selectList() {
        return userDao.selectList();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public boolean transfer(User fromUser, User toUser, int money) {

        if (null == fromUser || null == toUser || fromUser.getBalance() < money) {
            return false;
        }
        fromUser.setBalance(fromUser.getBalance() - money);
        toUser.setBalance(toUser.getBalance() + money);

        userDao.updateUser(fromUser);
        userDao.updateUser(toUser);

        return true;
    }
}
