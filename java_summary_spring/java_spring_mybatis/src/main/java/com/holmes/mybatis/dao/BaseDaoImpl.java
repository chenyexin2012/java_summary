package com.holmes.mybatis.dao;

import com.holmes.mybatis.pojo.BasePo;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class BaseDaoImpl extends SqlSessionDaoSupport implements IBaseDao {

    @Override
    public <T extends BasePo> T selectOne(T t) {
        String statement = t.getClass().getName() + ".selectOne";
        return getSqlSession().selectOne(statement, t);
    }

    @Override
    public <T extends BasePo> List<T> selectList(T t) {
        String statement = t.getClass().getName() + ".selectList";
        return getSqlSession().selectList(statement, t);
    }

    @Override
    public <T extends BasePo> void insert(T t) {
        String statement = t.getClass().getName() + ".insert";
        getSqlSession().insert(statement, t);
    }

    @Override
    public <T extends BasePo> void delete(T t) {
        String statement = t.getClass().getName() + ".delete";
        getSqlSession().insert(statement, t);
    }

    @Override
    public <T extends BasePo> void update(T t) {
        String statement = t.getClass().getName() + ".update";
        getSqlSession().insert(statement, t);
    }

    @Override
    public List<Map<String, Object>> selectMapList(String statement, Map<String, Object> map) {
        return getSqlSession().selectList(statement, map);
    }
}
