package com.ccit.service;

import com.ccit.mappers.UserMapper;
import com.ccit.pojo.User;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {
    @Inject
    private UserMapper userMapper;
    public User findUser(String username){
        return userMapper.findByUserName(username);
    }

}
