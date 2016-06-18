package com.ccit.entity;

import com.ccit.exception.DataException;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionManger {

    private static BasicDataSource dataSource = new BasicDataSource();
    static {
        Properties pro = new Properties();
        try {
            pro.load(ConnectionManger.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new DataException();
        }
        dataSource.setDriverClassName(pro.getProperty("jdbc.driver"));
        dataSource.setUrl(pro.getProperty("jdbc.url"));
        dataSource.setUsername(pro.getProperty("jdbc.username"));
        dataSource.setPassword(pro.getProperty("jdbc.password"));
    }
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
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
