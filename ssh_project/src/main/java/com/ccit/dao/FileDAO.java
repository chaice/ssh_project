package com.ccit.dao;

import com.ccit.pojo.File;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FileDAO extends BaseDAO<File ,Integer> {
    @Inject
    private SessionFactory sessionFactory;

    public Integer save(File file){
        Integer id  = (Integer) sessionFactory.getCurrentSession().save(file);
        return id;
    }
}
