package com.ccit.mappers;


import com.ccit.pojo.Movie;

import java.util.List;

public interface MovieMapper {
    Movie findById(Integer id);
    List<Movie> findAll();
    void insert(Movie movie);
    void deleteById(Integer id);
    void update(Movie movie);
}
