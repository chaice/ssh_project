package com.ccit.mappers;


import com.ccit.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    Book findByBookName(String bookname);
    Book findByBookId(Integer id);
    List<Book> findByPage(Map<String,Object>map);
    List<Book> findAll();
    void insert(Book book);
    void update(Book book);
    void deleteByBookId(Integer id);
    Long findParamCount(Map<String,Object> map);
}
