package com.ccit.mappers;


import com.ccit.pojo.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MovieMapper {
    Movie findById(Integer id);
    List<Movie> findAll();
    void insert(Movie movie);
    void deleteById(Integer id);
    void update(Movie movie);
    Movie findMap(Map<String,Object>para);
    Movie findParam(String title , String daoyan);
    List<Movie> findPage(Integer start , Integer size);
    ArrayList<Movie> findByIds(Integer...integers);
    void inserts(List<Movie>list);
    void deleteByIds(Integer...integers);
    List<Movie> findByIf(Map<String,Object>map);
}
