package com.ccit.test;

import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import com.ccit.utils.Page;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestBookService {
    Logger logger = LoggerFactory.getLogger(TestBookService.class);
    @Inject
    private BookService bookService;
    @Test
    public void testFindByPage(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("bookname","成为");
        map.put("start",1);
        map.put("size",5);
        List<Book> page =  bookService.findByParam(map);
        for (Book book:page){
            logger.debug("book:{}",book);
        }
    }

//    @Test
//    public void testCountByParam(){
//        Long count = bookService.countByParam("成为");
//        logger.debug("count:{}",count);
//    }
    @Test
    public void testFindById(){
        Book book = bookService.findById(6);
        logger.debug("book:{}",book);
    }
    @Test
    public void testAlterById(){
        Book book = bookService.findById(6);
        book.setBookauthor("cc");
        bookService.alterById(book);
    }
}
