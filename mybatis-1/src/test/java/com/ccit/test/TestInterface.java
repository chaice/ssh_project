package com.ccit.test;

import com.ccit.mappers.MovieMapper;
import com.ccit.pojo.Movie;
import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestInterface {
    Logger logger = LoggerFactory.getLogger(TestInterface.class);
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
    @Test
    public void testInsert(){
        Movie movie = new Movie("西游记");
        movie.setRate((float) 8.9);
        movie.setReleaseyear("1998");
        movieMapper.insert(movie);
        logger.debug("id为：{}",movie.getId());
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testFindMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("title","红楼梦");
        map.put("daoyan","咸鱼");
        logger.debug("Movie:{}",movieMapper.findMap(map));
        sqlSession.close();
    }
    @Test
    public void testFindParam(){
        logger.debug("result:{}",movieMapper.findParam("红楼梦","咸鱼"));
        sqlSession.close();
    }
    @Test
    public void testFindPage(){
        List<Movie> movieList = movieMapper.findPage(0,10);
        for (Movie movie:movieList) {
            logger.debug("movie:{}",movie);
        }
        sqlSession.close();
    }
    @Test
    public void testFindByIds(){
        ArrayList<Movie> movieList = movieMapper.findByIds(113,114,115);
        for (Movie movie:movieList) {
            logger.debug("movie:{}",movie);
        }
        sqlSession.close();
    }
    @Test
    public void testInserts(){
        List<Movie> list = new ArrayList<Movie>();
        Movie movie1 = new Movie("三国演义");
        Movie movie2 = new Movie("水浒传");
        Movie movie3 = new Movie("梵高");
        Movie movie4 = new Movie("西游记");
        list.add(movie1);
        list.add(movie2);
        list.add(movie3);
        list.add(movie4);

        movieMapper.inserts(list);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDeleteByIds(){
        movieMapper.deleteByIds(801,802,803,804,805,806,807,808);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testFindByIf(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title","");
        map.put("daoyan","咸鱼");
        List<Movie> list = movieMapper.findByIf(map);
        for (Movie movie: list) {
            logger.debug("movie:{}",movie);
        }
    }
}
