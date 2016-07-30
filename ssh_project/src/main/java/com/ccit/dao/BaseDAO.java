package com.ccit.dao;


import com.ccit.utils.QueryParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

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
        return  sessionFactory.getCurrentSession();
    }

    public List<T> findAll(){
        Criteria criteria = getSession().createCriteria(aClass);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    public T findById(pk id){
        return (T) getSession().get(aClass,id);
    }

    public void deleteById(pk id){
        getSession().delete(findById(id));
    }

    public void saveOrUpdate(T entity){
        getSession().saveOrUpdate(entity);
    }

    public void findAll(List<QueryParam> paramList,Integer p){
        Criteria criteria = getSession().createCriteria(aClass);
        for(QueryParam param : paramList){
            String type = param.getType();
            String propertyName = param.getPropertyName();
            Object value = param.getValue();
        }
    }

    public Long getCount(){

    }
}
