package com.ccit.dao;

import com.ccit.pojo.Movie;
import com.ccit.util.ReadBatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MovieDAO {
    private static SqlSession sqlSession = ReadBatis.testBatis();
    public void insert(Movie movie){
        sqlSession.insert("com.ccit.mappers.insert",movie);
        sqlSession.commit();
        sqlSession.close();
    }
    public void delete(Integer id){
        sqlSession.delete("com.ccit.mappers.deleteById",id);
        sqlSession.commit();
        sqlSession.close();
    }
    public void update(Movie movie){
        sqlSession.update("com.ccit.mappers.update",movie);
        sqlSession.commit();
        sqlSession.close();
    }
    public Movie findById(Integer id){
        return sqlSession.selectOne("com.ccit.mappers.findById",id);
    }
    public List<Movie> findAll(){
        return sqlSession.selectList("com.ccit.mappers.findAll");
    }
}
