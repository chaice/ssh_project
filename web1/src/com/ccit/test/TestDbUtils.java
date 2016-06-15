package com.ccit.test;


import com.ccit.entity.Admin;
import com.ccit.entity.ConnectionManger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestDbUtils {
    QueryRunner queryRunner = new QueryRunner();
    Connection connection = ConnectionManger.getConnection();
    @Test
    public void testConnection(){
        ConnectionManger.getConnection();
    }
    @Test
    public void testInsert(){
        String sql = "insert into admin(name,password) values(?,?)";
        try {
            queryRunner.update(connection,sql,"lili",123);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdate(){
        String sql = "update admin set name=? where `id`=?";
        try {
            queryRunner.update(connection,sql,"jim",7);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete(){
        String sql = "delete from admin where id=?";
        try {
            queryRunner.update(connection,sql,8);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testQueryById(){
        String sql = "select * from admin where id = ?";
        try {
            Admin ad =queryRunner.query(connection, sql, new BeanHandler<>(Admin.class),6);
            System.out.println(ad);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void testQueryAll(){
       String sql = "select * from admin";
        try {
           List<Admin> list= queryRunner.query(connection,sql,new BeanListHandler<>(Admin.class));
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testQueryByIdMap(){
        String sql = "select * from admin where id = ?";
        try {
           Map<String , Object> map = queryRunner.query(connection,sql,new MapHandler(),6);
            Set<Map.Entry<String, Object>> set = map.entrySet();
            System.out.println(set);
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testQueryAllMap(){
        String sql = "select * from admin";
        try {
           List<Map<String,Object>> list = queryRunner.query(connection,sql,new MapListHandler());
            for (Map<String,Object> map:list) {
                System.out.println(map);
            }
            System.out.println("--------------");
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testCount(){
        String sql = "select count(*) from admin";
        try {
           Long count =  queryRunner.query(connection,sql,new ScalarHandler<Long>());
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testQueryName(){
        String sql = "select `name` from admin";
        try {
            List<String> list = queryRunner.query(connection,sql,new ColumnListHandler<String>());
            System.out.println(list);
            for (String name:list) {
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
