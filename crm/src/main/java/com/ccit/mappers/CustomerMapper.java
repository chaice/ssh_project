package com.ccit.mappers;


import com.ccit.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<Customer> findByParams(Map<String, Object> params);

    Long countByParams(Map<String, Object> params);

    Long count();

    Customer findById(@Param("userid") Integer userid, @Param("companyid") Integer companyid);

    void insert(Customer customer);
}
