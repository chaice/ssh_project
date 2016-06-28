package com.ccit.test;

import com.ccit.mappers.MovieMapper;
import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestInterface {
    private static SqlSession sqlSession = MyBatis.getSqlSession();
   private static MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
    @Test
    public void testFindOne(){
        System.out.println(movieMapper.findById(794));
        sqlSession.close();
    }
    @Test
    public void testFindAll(){
        System.out.println(movieMapper.findAll());
        sqlSession.close();
    }
    @Test
    public void testDelete(){
        movieMapper.deleteById(794);
        sqlSession.commit();
        sqlSession.close();
    }
}
