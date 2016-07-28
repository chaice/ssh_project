package com.ccit.controller;

import com.ccit.pojo.Book;
import com.ccit.pojo.SearchParam;
import com.ccit.service.BookService;
import com.ccit.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Inject
    private BookService bookService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String booklist(Model model, @RequestParam(required = false,defaultValue ="1")Integer p,
                           HttpServletRequest request){
        List<SearchParam> paramList = SearchParam.getParam(request);
        Page<Book> page = bookService.findAll(paramList,p);
        model.addAttribute("page",page);
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
    @RequestMapping(value = "/update/{id:\\d+}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model){
        model.addAttribute("typelist",bookService.findAllBookType());
        model.addAttribute("publist",bookService.findAllPublisher());
        model.addAttribute("book",bookService.findById(id));
        return "update";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Book book){
        bookService.save(book);
        return "redirect:/book";
    }
}
