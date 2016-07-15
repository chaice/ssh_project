package com.ccit.mappers;


import com.ccit.pojo.Role;

import java.util.List;

public interface RoleMapper {
    Role findById(Integer id);

    List<Role> findAllRole();
}
