package com.holmes.spring.service;

import com.holmes.spring.dao.UserDao;
import com.holmes.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Resource(name = "hibernateTransactionManager")
    private PlatformTransactionManager transactionManager;

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public List<User> selectList() {
        return userDao.selectList();
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
//        int i = 1 / 0;

        userDao.updateUser(toUser);

        return true;
    }

    /**
     * 模拟转账 使用手动事务
     *
     * @param fromUser
     * @param toUser
     * @param money
     * @return
     */
    public boolean transfer2(User fromUser, User toUser, int money) {

        if (null == fromUser || null == toUser || fromUser.getBalance() < money) {
            return false;
        }

        DefaultTransactionDefinition definition = null;
        TransactionStatus status = null;
        try {
            definition = new DefaultTransactionDefinition();
            definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
            definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            status = transactionManager.getTransaction(definition);

            fromUser.setBalance(fromUser.getBalance() - money);
            toUser.setBalance(toUser.getBalance() + money);

            userDao.updateUser(fromUser);

            // 模拟异常
//            int i = 1 / 0;

            userDao.updateUser(toUser);

            transactionManager.commit(status);
        } catch (TransactionException e) {
            if (null != status) {
                transactionManager.rollback(status);
            }
        }
        return true;
    }
}
