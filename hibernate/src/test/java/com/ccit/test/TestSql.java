package com.ccit.test;


import com.ccit.pojo.User;
import com.ccit.utils.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestSql {
    @Test
    public void testAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List<Object[]> users = sqlQuery.list();//返回一个装Object数组的集合
        for(Object[] objects : users){
            System.out.println("id:"+objects[0]+"   username:"+objects[1]+"   password:"+objects[2]);
        }

        session.getTransaction().commit();
    }

}
