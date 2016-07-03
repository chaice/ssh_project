package com.ccit.service;


import com.ccit.exception.ServiceException;
import com.ccit.mappers.LoginDao;
import com.ccit.mappers.UserDao;
import com.ccit.pojo.Login;
import com.ccit.pojo.User;
import com.ccit.utils.Email;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginService {
    @Inject
    private UserDao userDao;
    @Inject
    private LoginDao loginDao;
    @Inject
    private Email email;

    public void login(String username,String password,String ip){
        User user = userDao.findByUsername(username);
        if(user != null && password.equals(user.getPassword())){
            loginDao.save(new Login(ip,user.getId()));
            String time = DateTime.now().toString("yyyy-MM-dd hh:mm:ss");
            email.sendEmail(username,ip,time);
        }else{
            throw new ServiceException("用户名或密码错误！！！");
        }
    }
}
