package com.ccit.controller;

import com.ccit.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        logger.debug("{}","Hello spring mvc");
        return "home";
    }
    @RequestMapping(value = "/login/{id:\\d+}",method = RequestMethod.GET)
    public String document(@PathVariable Integer id){
        logger.debug("id:{}"+id);
        return "login";
    }
    //使用model在控制器与视图之间进行传值
    @RequestMapping(value = "/home1",method = RequestMethod.GET)
    public String model(Model model){
        model.addAttribute("model","这是一个model");
        //在视图中直接使用${model}即可得到值
        return "login";
    }
    //使用ModelAndView进行传值
    @RequestMapping(value = "/{id;\\d+}",method = RequestMethod.GET)
    public ModelAndView modelAndView(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("ModelAndView","modelAndView进行传值");
        return modelAndView;
    }
    //userId、catalogId作为参数传入方法中,使用redirect达到请求转发
    @RequestMapping(value = "/users/{userId:\\d+}/pictures/catalog/{catalogId:\\d+}",
                    method = RequestMethod.GET)
    public String find(@PathVariable("userId") Integer userId,@PathVariable("catalogId") Integer catalogId){
        logger.debug("userId:{}",userId);
        logger.debug("catalogId:{}",catalogId);
        return "redirect:/home";
    }
    /*在控制器中获取表单内的值.eg:<input name="userName"/><input name="password"/>
    直接在控制方法内把两个input中的name属性值作为参数传入到方法内
    spring mvc 还提供自动把多个name属性值封装成一个对象,还可以自动进行数据类型转换
    创建一个User类 User中的属性有username,password.spring mvc会自动把两个name的属性封装成一个user
    对象.
    */
    @RequestMapping(value = "/form/value",method = RequestMethod.GET)
//    public String getFormValue(String username,String password){
//        logger.debug("userName:{}",username);
//        logger.debug("password:{}",password);
//        return "redirect:/home";
//    }
    public String getFormValue(User user){
        logger.debug("userName:{}",user.getUsername());
        logger.debug("password:{}",user.getPassword());
        return "redirect:/home";
    }
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String file(MultipartFile file){
        logger.debug("originName:{}",file.getOriginalFilename());
        logger.debug("fileSize:{}",file.getSize());
        logger.debug("mime:{}",file.getContentType());
        return "login";
    }
    @RequestMapping(value = "/cook",method = RequestMethod.GET)
    public String getCook(@CookieValue(value = "JSESSIONID")Cookie cookie){
        logger.debug("cookie:{}",cookie);
        return "login";
    }
    @ResponseBody
    @RequestMapping(value = "/check.json",method = RequestMethod.GET,
                    produces ="text/html;charset=UTF-8")
    public String getJson(@PathVariable Integer id){
        logger.debug("id:{}",id);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value ="/{id}.json",method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(){
        User user = new User("tom","123");
        return user;
    }
    @RequestMapping(value ="/getHeader",method = RequestMethod.GET)
    public String getHeader(@RequestHeader("Content-Type")String context){
        logger.debug("context:{}",context);
        return "login";
    }
}
