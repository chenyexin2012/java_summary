package com.holmes.spring.dao;

import com.holmes.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Component
//@Transactional
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(Long id) {

        Map map = jdbcTemplate.queryForMap("select *from user where id = " + id);
        if (null == map) {
            return null;
        }
        User user = new User();
        user.setId(id);
        user.setName(map.get("name") != null ? map.get("name").toString() : null);
        user.setBalance(map.get("balance") != null ? Integer.valueOf(map.get("balance").toString()) : null);
        return user;
    }

    public List<User> selectList() {

        List<User> list = jdbcTemplate.query("select *from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setBalance(rs.getInt("balance"));
                user.setName(rs.getString("name"));
                return user;
            }
        });
        return list;
    }

    public void addUser(User user) {

        PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory("insert into user(name, balance) "
                + "value(?,?)", new int[]{Types.VARCHAR, Types.INTEGER});
        jdbcTemplate.update(creatorFactory.newPreparedStatementCreator(new Object[]{
                user.getName(), user.getBalance()
        }));
    }

    public void updateUser(User user) {
        PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory("update user "
                + "set name = ?, balance=? where id=?", new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        jdbcTemplate.update(creatorFactory.newPreparedStatementCreator(new Object[]{
                user.getName(), user.getBalance(), user.getId()
        }));
    }
}
