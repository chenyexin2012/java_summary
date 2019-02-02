package com.holmes.mybatis.dao;

import com.holmes.mybatis.pojo.BasePo;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface IBaseDao {

    /**
     * 返回一条记录
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T extends BasePo> T selectOne(T t);

    /**
     * 返回一个list
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T extends BasePo> List<T> selectList(T t);

    /**
     * 新增
     *
     * @param t
     * @param <T>
     */
    public <T extends BasePo> void insert(T t);

    /**
     * 删除
     *
     * @param t
     * @param <T>
     */
    public <T extends BasePo> void delete(T t);

    /**
     * 更新
     *
     * @param t
     * @param <T>
     */
    public <T extends BasePo> void update(T t);

    /**
     * 返回一个map list
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> selectMapList(String statement, Map<String, Object> map);
}
