package com.ccit.entity;

import com.ccit.exception.DataException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class DbHelp {

    public static void doUpdate(String sql , Object...params){
        Connection connection = ConnectionManger.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection,sql,params);
        } catch (SQLException e) {
            throw new DataException();
        }finally {
            ConnectionManger.closeConnection(connection);
        }
    }
    public static <T> T doQuery(String sql, ResultSetHandler<T> handler, Object...params){
        Connection connection = ConnectionManger.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            return queryRunner.query(connection,sql,handler,params);
        } catch (SQLException e) {
            throw new DataException();
        }finally {
            ConnectionManger.closeConnection(connection);
        }
    }

}
