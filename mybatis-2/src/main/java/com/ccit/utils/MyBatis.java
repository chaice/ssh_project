package com.ccit.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatis {
    public static SqlSession getSqlSession(){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = (new SqlSessionFactoryBuilder().build(reader)).openSession();
        return sqlSession;
    }
}
