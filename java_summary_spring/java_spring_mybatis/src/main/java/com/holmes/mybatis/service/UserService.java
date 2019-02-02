package com.holmes.mybatis.service;

import com.holmes.mybatis.dao.BaseDaoImpl;
import com.holmes.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserService {

    @Autowired
    private BaseDaoImpl baseDao;

    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;

    public List<User> selectList(User user) {
        return baseDao.selectList(user);
    }

    public void insert(User user) {
        baseDao.insert(user);
    }

    public void deleteById(Integer userId) {
//        baseDao.deleteById(userId);
    }

    public void insertList(List<User> list) {
    }

    public void update(User user) {
        baseDao.update(user);
    }

}
