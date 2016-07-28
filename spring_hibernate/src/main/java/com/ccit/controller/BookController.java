package com.ccit.controller;

import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Inject
    private BookService bookService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String booklist(Model model){
        model.addAttribute("booklist",bookService.findAll());
        return "booklist";
    }
    @RequestMapping(value = "/delete/{id:\\d+}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        bookService.delete(id);
        return "redirect:/book";
    }
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("typelist",bookService.findAllBookType());
        model.addAttribute("publist",bookService.findAllPublisher());
        return "newbook";
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String add(Book book){
        bookService.save(book);
        return "redirect:/book";
    }
}
