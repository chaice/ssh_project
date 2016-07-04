package com.ccit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/home/{id}",method = RequestMethod.GET)
    public String home(@PathVariable("id")Integer id){
        logger.debug("id:{}",id);
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String username,String password){
       logger.debug("username:{}",username);
        logger.debug("password:{}",password);
        return "login";
    }
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String file(MultipartFile file){
        logger.debug("fileMIME:{}",file.getContentType());
        logger.debug("fileSize:{}",file.getSize());
        logger.debug("fileOriginName:{}",file.getOriginalFilename());
        return "index";
    }
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(@RequestHeader(value = "User-Agent")String context ){
        logger.debug("Content-Type:{}",context);
        return "user";
    }

}
