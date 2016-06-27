package com.ccit.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by cc on 2016/6/27.
 */
public class DBhelper {
    private Connection getConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/douban_movie?autoReconnect=true&useSSL=false";
            Connection connection = DriverManager.getConnection(url,"root","root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void doUpdate(String sql,Object...params){
       Connection connection = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public <T> T doQuerry(String sql,ResultSetHandler<T> handler,Object...params){
        Connection connection = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
          return queryRunner.query(connection,sql,handler,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
