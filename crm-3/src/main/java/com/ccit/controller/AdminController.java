package com.ccit.controller;


import com.ccit.pojo.DataTableResult;
import com.ccit.pojo.Role;
import com.ccit.pojo.User;
import com.ccit.service.UserService;
import com.ccit.utils.Strings;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
   Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Inject
    private UserService userService;
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String userManage(Model model){
        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user";
    }
    @ResponseBody
    @RequestMapping(value = "/user/dataList",method = RequestMethod.GET)
    public DataTableResult<User> getUserData(HttpServletRequest request,Model model){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String size = request.getParameter("length");
        String key = request.getParameter("username");
        String roleid = request.getParameter("roleid");
        logger.debug("key:{}",key);
        logger.debug("roleid:{}",roleid);
        key = Strings.getUTF8(key);
        Map<String,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("size",size);
        map.put("key",key);
        map.put("roleid",roleid);
        model.addAttribute("username",key);
        model.addAttribute("roleid",roleid);
        List<User> data = userService.finByParam(map);
        Long recordsTotal = userService.getTotal() ;
        Long recordsFiltered = userService.findByParamCount(map);
        DataTableResult<User> userTable = new DataTableResult<User>(draw,recordsTotal,recordsFiltered,data);
        return userTable;
    }
    @ResponseBody
    @RequestMapping(value = "/user/check",method = RequestMethod.GET)
    public String checkUserName(@RequestParam("username")String username){
        User user = userService.findByUserName(username);
        if(user == null){
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(User user,RedirectAttributes attributes){
        String password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(password);
        userService.addUser(user);
        attributes.addFlashAttribute("message","新增成功!");
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/reset/{id}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id")Integer id){
        User user = userService.findById(id);
        user.setPassword(DigestUtils.md5Hex("000000"));
        return "success";
    }

    @ResponseBody
    @RequestMapping(value ="alter/{id}",method = RequestMethod.GET)
    public User alterUser(@PathVariable("id")Integer id){
        User user = userService.findById(id);
        return user;
    }
    @ResponseBody
    @RequestMapping(value = "/alter",method = RequestMethod.POST)
    public String alterUser(User user){
        if(userService.alterUser(user)) {
            return "success";
        }
        return "";
    }
}
