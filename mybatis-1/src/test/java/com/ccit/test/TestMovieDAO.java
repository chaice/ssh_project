package com.ccit.test;


import com.ccit.dao.MovieDAO;
import com.ccit.pojo.Movie;
import org.junit.Test;


public class TestMovieDAO {
    private MovieDAO nd = new MovieDAO();
    @Test
    public void testInsert(){
        Movie movie = new Movie("三毛流浪记");
        movie.setReleaseyear("1998");
        nd.insert(movie);
    }
    @Test
    public void testFindOne(){
        System.out.println(nd.findById(134));
    }
    @Test
    public void testFindAll(){
        System.out.println(nd.findAll());
    }
    @Test
    public void testDelete(){
       nd.deleteById(795);
    }
    @Test
    public void testUpdate(){

    }
}
