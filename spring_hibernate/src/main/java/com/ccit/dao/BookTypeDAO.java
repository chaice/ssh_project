package com.ccit.dao;

import com.ccit.pojo.BookType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookTypeDAO {
    @Inject
    private SessionFactory sessionFactory;
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<BookType> findAll(){
        Criteria criteria = getSession().createCriteria(BookType.class);
        return  criteria.list();
    }
}
