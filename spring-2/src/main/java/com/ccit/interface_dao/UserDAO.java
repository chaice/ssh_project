package com.ccit.interface_dao;

import com.ccit.pojo.User;


public interface UserDao {

    User findByUserName(String userName);
}
