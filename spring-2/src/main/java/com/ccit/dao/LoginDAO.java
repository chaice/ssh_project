package com.ccit.dao;

import com.ccit.interface_dao.LoginDao;
import com.ccit.pojo.Login;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginDAO implements LoginDao {
    @Inject
    private JdbcTemplate template;
    public void save(Login login) {
        String sql = "insert into login(ip,userid) values(?,?)";
        template.update(sql,login.getIp(),login.getUserid());
    }
}
