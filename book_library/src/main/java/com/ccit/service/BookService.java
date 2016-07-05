package com.ccit.service;

import com.ccit.mappers.BookMapper;
import com.ccit.mappers.BookTypeMapper;
import com.ccit.mappers.PublisherMapper;
import com.ccit.pojo.Book;
import com.ccit.pojo.BookType;
import com.ccit.pojo.Publisher;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookService {
    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;

    public Book findByBookName(String bookname){
        return bookMapper.findByBookName(bookname);
    }
    public Book findByBookId(Integer id){
        return bookMapper.findByBookId(id);
    }
    public List<Book> findAll(){
        return bookMapper.findAll();
    }
    public void insert(Book book){
        bookMapper.insert(book);
    }
    public void update(Book book){
        bookMapper.update(book);
    }
    public void deleteByBookId(Integer id){
        bookMapper.deleteByBookId(id);
    }
    public List<BookType> findByBookTypeAll(){
        return bookTypeMapper.findByBookTypeAll();
    }

   public List<Publisher> findPublisherAll(){
       return publisherMapper.findAll();
   }

    public BookType findByBookType(String booktype){
        return bookTypeMapper.findByBookType(booktype);
    }
    public Publisher findByPubname(String pubname){
        return publisherMapper.findByPubname(pubname);
    }
}
