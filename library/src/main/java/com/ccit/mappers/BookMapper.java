package com.ccit.mappers;


import com.ccit.pojo.Book;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Book> findByParam(Map<String,Object>map);
    Long count();
    Long countByParam(Map<String,Object>map);
    void add(Book book);
    void deleteById(Integer id);
    Book findById(Integer id);
    void alterById(Book book);
}
