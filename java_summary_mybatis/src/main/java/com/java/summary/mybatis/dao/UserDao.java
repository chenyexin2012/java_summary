package com.java.summary.mybatis.dao;


import com.java.summary.mybatis.plugins.Page;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * @param userId
     * @return User
     */
    public User selectUserById(Integer userId);

    /**
     * @param user
     */
    public void insert(User user);

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

    /**
     *
     */
    public List<User> selectList(Page page);

    /**
     *
     */
    public List<User> selectListByAddressId(@Param("addressId") Long addressId, @Param("page") Page page);
}