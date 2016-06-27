package com.ccit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ReadXML {
    Logger logger = LoggerFactory.getLogger(ReadXML.class);
    @Test
    public void test() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sessionFactory.openSession();
//            Movie movie = sqlSession.selectOne("com.ccit.mappers.mappers.findById",111);
//            System.out.println(movie);
//            List<Movie> movieList = sqlSession.selectList("com.ccit.mappers.mappers.findAll");
//            for(Movie movie : movieList){
//                System.out.println(movie);
//            }
            sqlSession.delete("com.ccit.mappers.mappers.deleteById",112);
            sqlSession.commit();
            logger.debug("{}","执行删除操作");
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
