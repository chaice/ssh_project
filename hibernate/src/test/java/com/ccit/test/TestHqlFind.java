package com.ccit.test;


import com.ccit.pojo.User;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestHqlFind {

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();
        for (User user:userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    @Test
    public void testWhere(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

//        String hql = "from User where id = ?";
//        Query query = session.createQuery(hql);
//        query.setString(0,"56");//根据索引查,在HQL中索引从0开始,在sql中索引从1开始

        String hql = "from User where password = :pwd";//引用占位符,属性=:name
        Query query = session.createQuery(hql);
        query.setParameter("pwd","123123");
//        User user = (User) query.uniqueResult();//使用前提,确保结果集只有一个
        List<User> userList = query.list();
        for (User user:userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }
    @Test
    public void testOneColumn(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select username , password from User";
        Query query = session.createQuery(hql);
//        List<String> list = query.list();//只需要一列时
        List<Object[]> list = query.list();//多列返回一个装数组的list集合
        for (Object[] objects:list) {
            System.out.println("username:"+objects[0]+"   password:"+objects[1]);
        }

        session.getTransaction().commit();
    }
    //统计和分组查询
    @Test
    public void testCount(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);
        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println("count:"+objects[0]+"  max:"+objects[1]);

        session.getTransaction().commit();
    }
    //分页
    @Test
    public void testLimit(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        query.setFirstResult(5);
        query.setMaxResults(5);
        List<User> userList = query.list();
        for (User user:userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
//    @Test
//    public void testLike(){
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//
//        String hql = "from User where username like '%:name%'";
//        Query query = session.createQuery(hql);
//        query.setParameter("name","c");
//        List<User> userList = query.list();
//
//        for (User user:userList) {
//            System.out.println(user);
//        }
//        session.getTransaction().commit();
//    }
}
