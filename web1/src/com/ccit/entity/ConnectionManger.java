package com.ccit.entity;

import com.ccit.exception.DataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManger {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///library","root","911215");
            return  connection;
        } catch (ClassNotFoundException e) {
            throw  new DataException("数据库驱动异常",e);
        } catch (SQLException e) {
            throw new DataException("数据库连接异常", e);
        }
    }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataException("连接关闭异常",e);
        }
    }
}
