package com.ccit.mappers;


import com.ccit.pojo.Sales;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalesMapper {
    List<Sales> findByParams(Map<String, Object> params);

    Long countByParams(Map<String, Object> params);

    Long count();

    Long countUser(Integer userid);

    void add(Sales sales);

    Sales findById(@Param("id") Integer id, @Param("userid") Integer userid);

    void delete(Integer id);

    void update(Sales sales);
}
