package com.ccit.dao;

import com.ccit.pojo.Movie;
import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MovieDAO {
    private static SqlSession sqlSession = MyBatis.getSqlSession();
    public Movie findById(Integer id){
        Movie movie = sqlSession.selectOne("com.ccit.mappers.MovieMapper.findById",id);
        sqlSession.close();
        return movie;
    }
    public List<Movie> findAll(){
        List<Movie> list = sqlSession.selectList("com.ccit.mappers.MovieMapper.findAll");
        sqlSession.close();
        return list;
    }
    public void insert(Movie movie){
        sqlSession.insert("com.ccit.mappers.MovieMapper.insert",movie);
        sqlSession.commit();
        sqlSession.close();
    }
    public void update(Movie movie){
        sqlSession.update("com.ccit.mappers.MovieMapper.update",movie);
        sqlSession.commit();
        sqlSession.close();
    }
    public void deleteById(Integer id){
        sqlSession.delete("com.ccit.mappers.MovieMapper.deleteById",id);
        sqlSession.commit();
        sqlSession.close();
    }
}
