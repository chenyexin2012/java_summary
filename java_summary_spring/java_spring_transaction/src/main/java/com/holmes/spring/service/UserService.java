package com.holmes.spring.service;

import com.holmes.spring.dao.UserDao;
import com.holmes.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> selectList(User user) {
        return userDao.selectList(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 模拟转账
     *
     * @param fromUser
     * @param toUser
     * @param money
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean transfer(User fromUser, User toUser, int money) {

        if (null == fromUser || null == toUser || fromUser.getBalance() < money) {
            return false;
        }
        fromUser.setBalance(fromUser.getBalance() - money);
        toUser.setBalance(toUser.getBalance() + money);

        userDao.updateUser(fromUser);

        // 模拟异常
        int i = 1 / 0;

        userDao.updateUser(toUser);

        return true;
    }
}
