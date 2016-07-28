package com.ccit.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDAO<T,pk extends Serializable> {
    @Inject
    private SessionFactory sessionFactory;

    private Class<?> aClass;

    public BaseDAO(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        aClass = (Class<?>) type.getActualTypeArguments()[0];
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    public void save(T entity){
        getSession().saveOrUpdate(entity);
    }
    public T findOne(pk id){
       return (T) getSession().get(aClass,id);
    }
    public List<T> findAll(){
        Criteria criteria = getSession().createCriteria(aClass);
        return criteria.list();
    }
    public void delete(T entity){
        getSession().delete(entity);
    }
    public void delete(pk id){
        getSession().delete(findOne(id));
    }
}
