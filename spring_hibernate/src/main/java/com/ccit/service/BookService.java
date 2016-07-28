package com.ccit.service;

import com.ccit.dao.BookDAO;
import com.ccit.dao.BookTypeDAO;
import com.ccit.dao.PublisherDAO;
import com.ccit.pojo.Book;
import com.ccit.pojo.BookType;
import com.ccit.pojo.Publisher;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class BookService {
    @Inject
    private BookDAO bookDAO;
    @Inject
    private BookTypeDAO bookTypeDAO;
    @Inject
    private PublisherDAO publisherDAO;

    public List<Book> findAll(){
       return bookDAO.findAll();
    }

    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    public List<BookType> findAllBookType(){
        return bookTypeDAO.findAll();
    }
    public List<Publisher> findAllPublisher(){
        return publisherDAO.findAll();
    }

    public void save(Book book) {
        bookDAO.save(book);
    }

    public Book findById(Integer id) {
        return bookDAO.findOne(id);
    }
}
