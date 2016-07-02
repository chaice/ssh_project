package com.ccit.dao;

import com.ccit.interface_dao.UserDao;
import com.ccit.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserDAO implements UserDao {

    @Inject
    private JdbcTemplate template;
    public User findByUserName(String userName) {
        String sql = "select * from user where username = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),userName);
    }
}
