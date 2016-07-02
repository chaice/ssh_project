package com.ccit.service;

import com.ccit.dao.LoginDAO;
import com.ccit.dao.UserDAO;
import com.ccit.exception.LoginException;
import com.ccit.pojo.Login;
import com.ccit.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginService {
    @Inject
    private UserDAO userDAO;
    @Inject
    private LoginDAO loginDAO;
    @Inject
    private Email email;

    /**
     * 登录验证
     * @param username 用户名
     * @param password 用户密码
     * @param ip       用户IP
     */
    @Transactional
    public void login(String username,String password,String ip){
        User user = userDAO.findByUserName(username);
        if(user != null && user.getPassword().equals(password)){
            Login login = new Login(ip,user.getId());
            loginDAO.save(login);
//            email.sendEmail();
        }else{
            throw new LoginException("登录异常,用户名或密码错误！");
        }
    }
}
