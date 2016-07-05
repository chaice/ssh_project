package com.ccit.mappers;


import com.ccit.pojo.Book;

import java.util.List;

public interface BookMapper {
    Book findByBookName(String bookname);
    Book findByBookId(Integer id);
    List<Book> findAll();
    void insert(Book book);
    void update(Book book);
    void deleteByBookId(Integer id);

}
