package com.java.summary.mybatis.dao;


import com.java.summary.mybatis.plugins.Page;
import com.java.summary.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * @param map
     * @return
     */
    public List<User> selectList(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public List<User> selectList1(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public List<User> selectList2(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public List<User> selectList3(Map<String, Object> map);

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
     * @param userId
     */
    public void deleteById(Integer userId);

    /**
     * @param idList
     */
    public void deleteByIdList(@Param("idList") List<Integer> idList);

    /**
     * @param ids
     */
    public void deleteByIdArray(Integer ids[]);

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
     * @param user
     */
    public void update1(User user);

    /**
     * @param user
     */
    public void update2(User user);

    /**
     *
     */
    public List<User> selectListByAddressId(@Param("addressId") Long addressId, @Param("page") Page page);
}