package com.ccit.controller;

import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import com.ccit.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BooksController {
    Logger logger = LoggerFactory.getLogger(BooksController.class);
    @Inject
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET)
    public String bookList(Model model, @RequestParam(value = "p",required = false,defaultValue = "1")Integer p,
                           @RequestParam(value = "bookname",required = false)String bookname,
                           @RequestParam(value = "type",required = false)Integer typeid,
                           @RequestParam(value = "pub",required = false)Integer pubid){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("bookname",bookname);
        map.put("typeid",typeid);
        map.put("pubid",pubid);
        Page<Book> page = bookService.findByPage(p,map);
        model.addAttribute("bookname",bookname);
        model.addAttribute("typeid",typeid);
        model.addAttribute("pubid",pubid);
        model.addAttribute("page",page);
        model.addAttribute("bookTypeList",bookService.findByBookTypeAll());
        model.addAttribute("publisherList",bookService.findPublisherAll());
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
