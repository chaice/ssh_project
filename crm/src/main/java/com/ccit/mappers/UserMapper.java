package com.ccit.mappers;


import com.ccit.pojo.User;

public interface UserMapper {
    User findByUserName(String username);
    void alterPw(User user);
}
