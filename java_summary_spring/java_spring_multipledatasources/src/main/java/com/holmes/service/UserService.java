package com.holmes.service;

import com.holmes.dao.UserDao;
import com.holmes.datasource.DataSourceManager;
import com.holmes.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

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

    public List<User> selectList(Map<String, Object> map) {
        return userDao.selectList(map);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void deleteById(Integer userId) {
//        userDao.deleteById(userId);
    }

    public void insertList(List<User> list) {
        System.out.println(">>>>>>>>>>>>>>>>>" + DataSourceManager.get());
        userDao.insertList(list, "");
        System.out.println(">>>>>>>>>>>>>>>>>" + DataSourceManager.get());
        userDao.insertList(list, "");
        System.out.println(">>>>>>>>>>>>>>>>>" + DataSourceManager.get());
        userDao.insertList(list, "");
        System.out.println(">>>>>>>>>>>>>>>>>" + DataSourceManager.get());
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void copyData() {

        Map<String, Object> paramMap = new HashMap<>();
        List<User> userList = userDao.selectList(paramMap);
        if (null != userList && userList.size() > 0) {
            insertList(userList);
            insertList(userList);
        }
    }
}
