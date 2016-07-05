package com.ccit.controller;

import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    Logger logger = LoggerFactory.getLogger(BooksController.class);
    @Inject
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET)
    public String bookList(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        return"books/list";
    }
    @RequestMapping(value = "/{id}/del",method = RequestMethod.GET)
    public String bookDel(@PathVariable("id") Integer id, RedirectAttributes attributes){
        bookService.deleteByBookId(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/books";
    }
    @RequestMapping(value = "/new/{id}",method = RequestMethod.GET)
    public String bookUpdate(@PathVariable("id")Integer id,Model model){
        Book book = bookService.findByBookId(id);
        model.addAttribute("book",book);
        model.addAttribute("bookTypeList",bookService.findByBookTypeAll());
        model.addAttribute("publisherList",bookService.findPublisherAll());
        return "books/update";
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String bookUpdate(Book book,RedirectAttributes attributes){
        bookService.update(book);
        attributes.addFlashAttribute("message","修改成功！");
        return "redirect:/books";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String bookAdd(Model model){
        model.addAttribute("bookTypeList",bookService.findByBookTypeAll());
        model.addAttribute("publisherList",bookService.findPublisherAll());
        return "books/add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String bookAdd(Book book,RedirectAttributes attributes){
        bookService.insert(book);
        attributes.addFlashAttribute("message","添加成功！");
        return "redirect:/books";
    }

}
