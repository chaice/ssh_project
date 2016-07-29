package com.ccit.controller;

import com.ccit.pojo.Office;
import com.ccit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/office")
public class OfficeController {

    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String office(Model model){
        model.addAttribute("officeList",userService.findAllOffice());
        return "office/office";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Office office){
        userService.addOffice(office);
        return "redirect:/office";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        userService.deleteOffice(id);
        return "redirect:/office";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Office update(@PathVariable Integer id){
       return userService.findOffice(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Office office){
        userService.addOffice(office);
        return "redirect:/office";
    }
}
