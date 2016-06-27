package com.ccit.dao;


import com.ccit.entity.Movie;
import com.ccit.utils.CacheUtil;
import com.ccit.utils.DBhelper;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
public class MovieDAO {
    private Logger logger = LoggerFactory.getLogger("MovieDAO.class");
    DBhelper dBhelper = new DBhelper();
    public void insert(Movie movie){
        String sql = "insert into movie(title,rate,releaseyear,sendtime,daoyan,jianjie) values(?,?,?,?,?,?)";
        dBhelper.doUpdate(sql,movie.getTitle(),movie.getRate(),movie.getReleaseyear(),movie.getSendtime(),movie.getDaoyan(),movie.getJianjie());
    }
    public Movie findOne(int id){
        Movie movie = (Movie) CacheUtil.get("Movie"+id);
        logger.debug("{}","cache");
        if(movie == null) {
            String sql = "select * from movie where id = ?";
            movie = dBhelper.doQuerry(sql, new BeanHandler<Movie>(Movie.class), id);
            CacheUtil.set("Movie"+id,movie);
            logger.debug("sql{}",sql);
        }
        return movie;
    }
    public List<Movie> findAll(){
        String sql = "select * from movie";
        return dBhelper.doQuerry(sql,new BeanListHandler<Movie>(Movie.class));
    }
}
