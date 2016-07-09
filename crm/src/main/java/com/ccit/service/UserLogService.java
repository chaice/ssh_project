package com.ccit.service;


import com.ccit.mappers.UserLogMapper;
import com.ccit.pojo.UserLog;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UserLogService {
    @Inject
    private UserLogMapper logMapper;
    public Long getTotal(){
        return logMapper.getTotal();
    }
    public List<UserLog> findAll(String start,String size){
        return logMapper.findAll(start,size);
    }

}
