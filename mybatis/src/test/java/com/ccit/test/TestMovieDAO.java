package com.ccit.test;

import com.ccit.dao.MovieDAO;
import com.ccit.pojo.Movie;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class TestMovieDAO {
    Logger logger = LoggerFactory.getLogger(TestMovieDAO.class);
    private MovieDAO md = new MovieDAO();
    @Test
    public void testInsert(){
        Movie movie = new Movie();
        movie.setTitle("水浒传");
        movie.setRate((float) 7.8);
        movie.setReleaseyear("1998");
        md.insert(movie);
        logger.debug("{}",movie);
    }
    @Test
    public void testDelete(){
        md.delete(793);
        logger.debug("delete{}",793);
    }
    @Test
    public void testUpdate(){
        Movie movie = new Movie();
        movie.setId(794);
        movie.setTitle("红楼梦");
        md.update(movie);
        logger.debug("title update为：{}","红楼梦");
    }
    @Test
    public void testFindById(){
        Movie movie = md.findById(794);
        logger.debug("{}",movie);
    }
    @Test
    public void testFindAll(){
        List<Movie> movieList = md.findAll();
        Iterator iterator = movieList.iterator();
        while(iterator.hasNext()){
            logger.debug("{}",iterator.next());
        }
    }
}
