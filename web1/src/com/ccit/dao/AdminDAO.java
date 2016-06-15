package com.ccit.dao;

import com.ccit.entity.Admin;
import com.ccit.entity.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class AdminDAO {
    public void insert(Admin ad){
        String sql = "insert into admin(name,password) values(?,?)";
        DbHelp.doUpdate(sql,ad.getName(),ad.getPassword());
    }
    public void delete(Integer id){
        String sql = "delete from admin where `id` = ?";
        DbHelp.doUpdate(sql,id);
    }
    public void update(Admin ad){
        String sql = "update admin set name=?,password=? where `id`=?";
        DbHelp.doUpdate(sql,ad.getName(),ad.getPassword(),ad.getId());
    }
    public Admin findOne(Integer id){
        String sql = "select * from admin where `id`=?";
        return DbHelp.doQuery(sql,new BeanHandler<>(Admin.class),id);
    }
    public List<Admin> findAll(){
        String sql = "select * from admin";
        return DbHelp.doQuery(sql,new BeanListHandler<>(Admin.class));
    }
}
