package com.ccit.dao;


import com.ccit.utils.Page;
import com.ccit.utils.QueryParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;

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

    public Session getSession(){
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

    public Page<T> findAll(List<QueryParam> paramList, Integer p){
        Criteria criteria = getSession().createCriteria(aClass);
        return getPage(paramList, p, criteria);
    }
    public Page<T> findAll(Criteria criteria,List<QueryParam> paramList, Integer p){
        return getPage(paramList, p, criteria);
    }

    private Page<T> getPage(List<QueryParam> paramList, Integer p, Criteria criteria) {
        for(QueryParam param : paramList){
            String type = param.getType();
            String propertyName = param.getPropertyName();
            Object value = param.getValue();

            if(propertyName.contains("_or_")){
                String[] paramnames = propertyName.split("_or_");
                Disjunction disjunction = Restrictions.disjunction();
                for(String name :paramnames){
                    disjunction.add(Restrictions.like(name, (String) value, MatchMode.ANYWHERE));
                }
                criteria.add(disjunction);
            }else{
               Criterion criterion = bulitRestrictions(type, propertyName, value);
                criteria.add(criterion);
            }
        }
        Integer count = getCount(criteria).intValue();
        Page<T> page = new Page<T>(p,count,10);

        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getSize());
        criteria.addOrder(Order.desc("id"));

        List<T> items = criteria.list();

        page.setItems(items);

        return page;
    }

    private Criterion bulitRestrictions(String type, String propertyName, Object value) {
        if("eq".equals(type)){
            return Restrictions.eq(propertyName,value);
        }else if("like".equals(type)){
            return Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE);
        }else if("ge".equals(type)){
            return Restrictions.ge(propertyName,value);
        } else if("le".equals(type)){
            return Restrictions.le(propertyName,value);
        }
        return null;
    }

    public Long getCount(Criteria criteria){
        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;
        criteria.setProjection(Projections.rowCount());

        Long count = (Long) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(resultTransformer);

        return count;
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }
}
