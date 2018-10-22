package com.java.summary.springboot.mybatis.mapper;


import com.java.summary.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

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
    public List<User> selectList();

    /**
     *
     */
    public List<User> selectListByAddressId(@Param("addressId") Long addressId);
}