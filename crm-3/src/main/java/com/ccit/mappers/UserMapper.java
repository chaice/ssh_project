package com.ccit.mappers;


import com.ccit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    void alterPw(User user);
    List<User> findByParam(Map<String, Object> param);
    Long findByParamCount(Map<String, Object> param);
    Long getTotal();
    void addUser(User user);
    void deleteById(Integer id);
    User findById(Integer id);
    User findByUserName(String username);
    Integer alterUser(User user);
    List<User> findAll();
}
