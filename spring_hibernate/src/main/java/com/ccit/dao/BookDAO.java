package com.ccit.dao;

import com.ccit.pojo.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookDAO {
    @Inject
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    public void save(Book book){
        getSession().saveOrUpdate(book);
    }
    public Book findOne(Integer id){
        return (Book) getSession().get(Book.class,id);
    }
    public List<Book> findAll(){
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.addOrder(Order.desc("id"));
        return  criteria.list();
    }
    public void delete(Book book){
        getSession().delete(book);
    }
    public void delete(Integer id){
        getSession().delete(findOne(id));
    }
}

