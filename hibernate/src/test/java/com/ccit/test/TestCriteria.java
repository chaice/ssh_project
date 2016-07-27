package com.ccit.test;


import com.ccit.pojo.User;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class TestCriteria {
    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);

        List<User> list = criteria.list();
        for (User user:list) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    @Test
    public void testFindWhere(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);

        //and ,以下两条件为and关系
//        criteria.add(Restrictions.eq("password","123123"));
//        criteria.add(Restrictions.eq("username","cc"));

        //or
//        criteria.add(Restrictions.or(Restrictions.eq("password","123123"),Restrictions.eq("username","cc")));

        //or
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("password","123123"));
        disjunction.add(Restrictions.eq("username","cc"));
        criteria.add(disjunction);

        //in
        criteria.add(Restrictions.in("username",new Object[]{"cc","小明"}));
        List<User> list = criteria.list();
        for (User user:list) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    /*模糊查询
    like(属性名,值,在什么位置)
     */
    @Test
    public void testFindLByLike(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.like("username","c", MatchMode.ANYWHERE));
        User user = (User) criteria.uniqueResult();

        System.out.println(user);

        session.getTransaction().commit();
    }
    @Test
    public void testLimit(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("password","123123"));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);
        List<User> userList = criteria.list();
        for (User user:userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    @Test
    public void testCount(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        //使用projections 下一个会覆盖上一个
//        criteria.setProjection(Projections.count("username"));
//        criteria.setProjection(Projections.rowCount());

//        Long count = (Long) criteria.uniqueResult();
//        System.out.println(count);
        //使用projectionsList
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.max("id"));
        projectionList.add(Projections.rowCount());

        criteria.setProjection(projectionList);

        Object[] objects = (Object[]) criteria.uniqueResult();

        System.out.println("max:"+objects[0]+"count:"+objects[1]);

        session.getTransaction().commit();
    }
}
