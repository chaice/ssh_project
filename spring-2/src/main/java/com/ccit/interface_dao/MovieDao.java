package com.ccit.interface_dao;


import com.ccit.pojo.Movie;

import java.util.List;

public interface MovieDao {
    void delete(Integer id);
    void update(Movie movie);
    void insert(Movie movie);
    Movie findById(Integer id);
    List<Movie> findAll();
    Movie findByTitle(String title);
}
