package com.ccit.dao;


import com.ccit.entity.DbHelp;
import com.ccit.entity.FileUp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class FileDAO {
    public void insert(FileUp file){
        String sql = "insert into t_file(filename,realname,md5,size) values(?,?,?,?)";
        DbHelp.doUpdate(sql,file.getFilename(),file.getRealname(),file.getMd5(),file.getSize());
    }
    public FileUp findById(Integer id){
        String sql = "select * from t_file where id = ?";
        return DbHelp.doQuery(sql,new BeanHandler<>(FileUp.class),id);
    }
    public List<FileUp> findAll(){
        String sql = "select * from t_file";
        return DbHelp.doQuery(sql,new BeanListHandler<>(FileUp.class));
    }
    public List<FileUp> findByMd5(String md5){
        String sql = "select * from t_file where md5 = ?";
        return DbHelp.doQuery(sql,new BeanListHandler<>(FileUp.class),md5);
    }
}
