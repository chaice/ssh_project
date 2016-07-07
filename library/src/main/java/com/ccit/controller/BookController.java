package com.ccit.controller;


import com.ccit.pojo.Book;
import com.ccit.service.BookService;
import com.ccit.utils.Strings;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {
    @Inject
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("bookTypeList",bookService.findAllType());
        model.addAttribute("publisherList",bookService.findAllPub());
        return "/books/list";
    }
    @RequestMapping( value ="/data.json" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        String draw= request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String bookname = Strings.toUTF8(request.getParameter("bookName"));
        String typeid = request.getParameter("type");
        String pubid = request.getParameter("pub");

        String index = request.getParameter("order[0][column]");
        String dir = request.getParameter("order[0][dir]");
        String columeName = request.getParameter("columns["+index+"][name]");
        Map<String,Object> param = Maps.newHashMap();
        param.put("bookname",bookname);
        param.put("start",start);
        param.put("size",length);
        param.put("typeid",typeid);
        param.put("pubid",pubid);
        param.put("columnName",columeName);
        param.put("dir",dir);

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordsFiltered",bookService.countByParam(param));
        result.put("data",bookService.findByParam(param));
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String add(Book book){
        bookService.add(book);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/delete/{id:\\d+}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")Integer id){
        bookService.delete(id);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/alter/{id:\\d+}",method = RequestMethod.GET)
    public Book alter(@PathVariable("id")Integer id){
        Book book = bookService.findById(id);
        return book;
    }
    @ResponseBody
    @RequestMapping(value = "/alter",method = RequestMethod.POST)
    public String alter(Book book){
        bookService.alterById(book);
        return "success";
    }
}
