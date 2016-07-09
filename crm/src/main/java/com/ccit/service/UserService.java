package com.ccit.service;

import com.ccit.mappers.UserLogMapper;
import com.ccit.mappers.UserMapper;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {
    @Inject
    private UserMapper userMapper;
    @Inject
    private UserLogMapper logMapper;
    public User findUser(String username){
        return userMapper.findByUserName(username);
    }

    public void alterPw(String password) {
        User user = ShiroUtil.getPrincipal();
        user.setPassword(password);
        userMapper.alterPw(user);
    }

    public boolean addLog(String ip) {
        User user = ShiroUtil.getPrincipal();
        String logintime = DateTime.now().toString("yyyy-MM-hh ss:mm:ss");
        int i = logMapper.insert(user.getId(),ip,logintime);
        if(i == 1){
            return true;
        }
        return false;
    }
}
