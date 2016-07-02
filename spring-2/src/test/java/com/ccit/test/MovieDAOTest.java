package com.ccit.test;

import com.ccit.dao.MovieDAO;
import com.ccit.pojo.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class MovieDAOTest {
    @Inject
    private MovieDAO movieDAO;
    @Test
    public void testDelete(){
        movieDAO.delete(798);
    }
    @Test
    public void testFindById(){
        Movie movie = movieDAO.findById(779);
        Assert.assertNotNull(movie);
    }
    @Test
    public void testFindAll(){
        List<Movie> list = movieDAO.findAll();
        Assert.assertEquals(list.size(),680);
    }
    @Test
    public void testFindByTitle(){
        Movie movie = movieDAO.findByTitle("大都会");
        Assert.assertNotNull(movie);
        System.out.println(movie);
    }
}
