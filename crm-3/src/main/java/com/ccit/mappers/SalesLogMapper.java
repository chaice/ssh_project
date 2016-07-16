package com.ccit.mappers;


import com.ccit.pojo.SalesLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalesLogMapper {
    List<SalesLog> findBySalesId(Integer salesid);

    void add(SalesLog salesLog);

    SalesLog findByid(Integer id);
}
