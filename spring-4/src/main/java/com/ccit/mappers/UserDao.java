package com.ccit.mappers;

import com.ccit.pojo.User;

public interface UserDao {
    User findByUserName(String userName);
}
