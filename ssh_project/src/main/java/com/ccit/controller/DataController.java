package com.ccit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class DataController {

    @RequestMapping(method = RequestMethod.GET)
    public String datacount(){
        return "data/datacount";
    }
    @ResponseBody
    @RequestMapping(value = "ill.json",method = RequestMethod.GET)
    public String illList(){
        return "";
    }
}
