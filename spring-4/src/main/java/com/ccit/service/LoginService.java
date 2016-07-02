package com.ccit.service;


import com.ccit.exception.ServiceException;
import com.ccit.mappers.LoginDao;
import com.ccit.mappers.UserDao;
import com.ccit.pojo.Login;
import com.ccit.pojo.User;
import com.ccit.utils.Email;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class LoginService {
    @Inject
    private UserDao userDao;
    @Inject
    private LoginDao loginDao;
    @Inject
    private Email email;
    public void login(String userName,String password,String ip){
        User user = userDao.findByUserName(userName);
        if(user != null && user.getPassword().equals(password)){
            loginDao.save(new Login(ip,user.getId()));
            email.sendEmail();
        }else{
            throw new ServiceException("用户名或密码错误！！！");
        }
    }
}
