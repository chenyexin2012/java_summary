package com.holmes.dao;


import com.holmes.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//@Transactional
public interface UserDao {

    /**
     * @param map
     * @return
     */
    public List<User> selectList(Map<String, Object> map);

    /**
     * @param user
     */
    public void insert(User user);

    /**
     * @param userId
     */
    public void deleteById(Integer userId);

    /**
     * 批量插入
     *
     * @param list
     */
    public void insertList(List<User> list);

    /**
     * @param user
     */
    public void update(User user);
}