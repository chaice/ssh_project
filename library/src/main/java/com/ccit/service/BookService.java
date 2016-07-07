package com.ccit.service;


import com.ccit.mappers.BookMapper;
import com.ccit.mappers.BookTypeMapper;
import com.ccit.mappers.PublisherMapper;
import com.ccit.pojo.Book;
import com.ccit.pojo.BookType;
import com.ccit.pojo.Publisher;
import com.ccit.utils.Page;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class BookService {
    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;
    public List<Book> findByParam(Map<String,Object>param){
        return bookMapper.findByParam(param);
    }
    public Long count(){
        return bookMapper.count();
    }
    public Long countByParam(Map<String,Object>map){
        return bookMapper.countByParam(map);
    }
    public List<Publisher> findAllPub(){
        return publisherMapper.findAll();
    }
    public List<BookType> findAllType(){
        return bookTypeMapper.findAll();
    }
    public void add(Book book){
        bookMapper.add(book);
    }
    public void delete(Integer id){
        bookMapper.deleteById(id);
    }
    public Book findById(Integer id){
        return bookMapper.findById(id);
    }
    public void alterById(Book book){
        bookMapper.alterById(book);
    }
}
