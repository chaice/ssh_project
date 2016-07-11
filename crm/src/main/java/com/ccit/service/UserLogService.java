package com.ccit.service;


import com.ccit.mappers.UserLogMapper;
import com.ccit.pojo.User;
import com.ccit.pojo.UserLog;
import com.ccit.utils.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UserLogService {
    @Inject
    private UserLogMapper logMapper;
    public Long getTotal(){
        User user = ShiroUtil.getPrincipal();
        return logMapper.getTotal(user.getId());
    }
    public List<UserLog> findAll(String start,String size){
        User user = ShiroUtil.getPrincipal();
        return logMapper.findAll(user.getId(),start,size);
    }

}
