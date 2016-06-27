package com.ccit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.IOException;
import java.io.Reader;

/*
使用Mybatis的步骤
1.添加mybatis依赖
2.创建 mybatis.xml
3.创建**mapper.xml,并向mybatis.xml中添加
4.读取mybatis.xml
Reader reader = Resources.getResourceAsReader("mybatis.xml");
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
SqlSession sqlsession = sessionFactory.openSession();
 */
public class ReadBatis {
    public static SqlSession testBatis(){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession;
    }
}
