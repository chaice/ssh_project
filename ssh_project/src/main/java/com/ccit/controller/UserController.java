package com.ccit.controller;

import com.ccit.pojo.User;
import com.ccit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/user")
public class UserController {
    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String userList(Model model){
        model.addAttribute("userList",userService.findAll());
        model.addAttribute("roleList",userService.findAllRole());
        return "user/userlist";
    }
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    public String add(User user){
        userService.add(user);
        return "redirect:/user";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user";
    }
    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public User update(@PathVariable Integer id){
        User user = userService.findById(id);
        return user;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user){
        userService.add(user);
        return "redirect:/user";
    }

}
