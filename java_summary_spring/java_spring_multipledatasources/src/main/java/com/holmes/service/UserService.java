package com.holmes.service;

import com.holmes.dao.UserDao;
import com.holmes.datasource.DataSourceManager;
import com.holmes.enums.DataSourceType;
import com.holmes.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;

    public List<User> selectList(Map<String, Object> map, DataSourceType dataSourceType) {
        if (null != dataSourceType) {
            DataSourceManager.set(dataSourceType);
        }
        return userDao.selectList(map);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void deleteById(Integer userId) {
        userDao.deleteById(userId);
    }

    public void insertList(List<User> list) {
        userDao.insertList(list);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void copyData() {

        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            DataSourceManager.set(DataSourceType.DATA_SOURCE_A);
            Map<String, Object> paramMap = new HashMap<>();
            List<User> userList = userDao.selectList(paramMap);
            if (null != userList) {
                DataSourceManager.set(DataSourceType.DATA_SOURCE_B);
                this.insertList(userList);
                DataSourceManager.set(DataSourceType.DATA_SOURCE_C);
                this.insertList(userList);
            }
            int i = 1 / 0;
            transactionManager.commit(status);
        } catch (Exception e) {
            if (null != status) {
                transactionManager.rollback(status);
            }
            throw e;
        }
    }
}
