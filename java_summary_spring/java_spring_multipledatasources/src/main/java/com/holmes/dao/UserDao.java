package com.holmes.dao;


import com.holmes.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */

public interface UserDao {

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    public List<User> selectList(Map<String, Object> map);

    /**
     * 新增
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(User user);

    /**
     * 删除
     *
     * @param userId
     * @param dataSourceFlag
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@Param("userId") Integer userId, @Param("dataSourceFlag") String dataSourceFlag);

    /**
     * 批量插入
     *
     * @param list
     * @param dataSourceFlag
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertList(@Param("list") List<User> list, @Param("dataSourceFlag") String dataSourceFlag);

    /**
     * 更新
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(User user);
}