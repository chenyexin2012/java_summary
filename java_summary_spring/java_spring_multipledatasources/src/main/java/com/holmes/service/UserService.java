package com.holmes.service;

import com.holmes.dao.UserDao;
import com.holmes.datasource.DataSourceManager;
import com.holmes.enums.DataSourceType;
import com.holmes.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<User> selectList(Map<String, Object> map, DataSourceType dataSourceType) {
        if(null != dataSourceType) {
            DataSourceManager.set(dataSourceType);
        }
        return userDao.selectList(map);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(User user) {
        userDao.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer userId) {
        userDao.deleteById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<User> list) {
        userDao.insertList(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        userDao.update(user);
    }

//    @Transactional(rollbackFor = Exception.class)
    public void copyData() {

        DataSourceManager.set(DataSourceType.DATA_SOURCE_A);
        Map<String, Object> paramMap = new HashMap<>();
        List<User> userList = userDao.selectList(paramMap);
        if(null != userList) {
            DataSourceManager.set(DataSourceType.DATA_SOURCE_B);
            this.insertList(userList);
            DataSourceManager.set(DataSourceType.DATA_SOURCE_C);
            this.insertList(userList);
        }
    }
}
