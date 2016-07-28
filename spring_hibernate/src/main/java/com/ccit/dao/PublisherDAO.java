package com.ccit.dao;

import com.ccit.pojo.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PublisherDAO {
    @Inject
    private SessionFactory sessionFactory;
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Publisher> findAll(){
        Criteria criteria = getSession().createCriteria(Publisher.class);
        return criteria.list();
    }
}
