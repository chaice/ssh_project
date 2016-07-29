package com.ccit.controller;

import com.ccit.pojo.Ill;
import com.ccit.service.IllService;
import com.ccit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/ill")
public class IllController {
    @Inject
    private IllService illService;
    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String ill(Model model){
        model.addAttribute("officeList",userService.findAllOffice());
        model.addAttribute("illList",illService.findAll());
        return "ill/ill";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Ill ill){
        illService.saveOrUpdate(ill);
        return "redirect:/ill";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        illService.delete(id);
        return "redirect:/ill";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public Ill update(@PathVariable Integer id){
        return illService.findById(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Ill ill){
        illService.saveOrUpdate(ill);
        return "redirect:/ill";
    }

}
