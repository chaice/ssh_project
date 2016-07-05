package com.ccit.test;

import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestBookService {
    @Inject
    private BookService bookService;
    @Test
    public void testFindByBookName(){
        Book book = bookService.findByBookName("成为乔布斯");
        Assert.assertNotNull(book);
    }
    @Test
    public void testFindByBookId(){
        Book book = bookService.findByBookId(1);
        Assert.assertNotNull(book);
    }
    @Test
    public void testFindAll(){
        List<Book> bookList = bookService.findAll();
        Assert.assertEquals(bookList.size(),29);
    }
    @Test
    public void testInsert(){
        Book book = new Book();
        book.setBookname("sql必知必会");
        book.setBookauthor("cc");
        book.setBookprice((float) 45.3);
        book.setBooknum(111);
        book.setPubid(1);
        book.setTypeid(2);
        bookService.insert(book);
    }
    @Test
    public void testUpdate(){
        Book book = bookService.findByBookId(36);
        book.setBookprice((float)45.4);
        bookService.update(book);
    }
    @Test
    public void testDeleteByBookId(){
        bookService.deleteByBookId(1);
    }
}
