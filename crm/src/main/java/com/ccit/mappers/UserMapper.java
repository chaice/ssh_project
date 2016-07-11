package com.ccit.mappers;


import com.ccit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User findByUserName(String username);
    void alterPw(User user);
    List<User> findByParam(Map<String,Object>param);
    Long findByParamCount(Map<String,Object>param);
    Long getTotal();
    void addUser(User user);
    void deleteById(Integer id);
    User findById(Integer id);

    Integer alterUser(User user);
}
