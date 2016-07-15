package com.ccit.mappers;


import com.ccit.pojo.UserLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.plugin.Interceptor;

import java.util.List;

public interface UserLogMapper {
    int insert(@Param("userid") Integer id, @Param("ip") String ip, @Param("logintime") String logintime);
    Long getTotal(@Param("userid") Integer userid);
    List<UserLog> findAll(@Param("userid") Integer userid, @Param("start") String start, @Param("size") String size);
}
