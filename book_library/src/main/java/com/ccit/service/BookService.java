package com.ccit.service;

import com.ccit.mappers.BookMapper;
import com.ccit.mappers.BookTypeMapper;
import com.ccit.mappers.PublisherMapper;
import com.ccit.pojo.Book;
import com.ccit.pojo.BookType;
import com.ccit.pojo.Publisher;
import com.ccit.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);
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
    public Page<Book> findByPage(Integer page,Map<String,Object>map){
        int count = bookMapper.findParamCount(map).intValue();
        if(count == 0){
            return null;
        } else{
            Page<Book> bookPage = new Page<Book>(page,5,count);
            map.put("start",bookPage.getStart());
            map.put("num",5);
            bookPage.setItems(bookMapper.findByPage(map));
            return bookPage;
        }

    }
}
