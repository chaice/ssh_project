package com.ccit.test;

import com.ccit.dao.MovieDAO;
import com.ccit.entity.Movie;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;

import java.util.List;

/**
 * Created by cc on 2016/6/27.
 */
public class TestDAO {
    @Test
    public void movieDAO(){
        MovieDAO md = new MovieDAO();
//        List<Movie> list = md.findAll();
//        for (Movie movie:list) {
//            System.out.println(movie);
//        }
        md.findOne(111);
        md.findOne(111);
        md.findOne(111);
        md.findOne(111);
        md.findOne(111);


//        CacheManager cacheManager = new CacheManager();
//        Ehcache ehcache = cacheManager.getEhcache("myCache");
//        Element element = new Element("user","1");
//        ehcache.put(element);
//        Element element1 = ehcache.get("user");
//        String str = null;
//        if(element1 != null){
//            str = (String) element1.getObjectValue();
//        }
//        System.out.println(str);
    }
}
