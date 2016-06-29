package com.ccit.mappers;


import com.ccit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findByIf(Map<String,Object>map);
    User findById(Integer id);
    List<User> findByIds(Integer...integers);
    void deleteByIds(Integer...integers);
    void inserts(List<User>list);
    User findUser(Integer id);
    List<User> findResultMapById(Integer id);
}
