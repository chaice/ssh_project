package com.ccit.controller;

import com.ccit.pojo.LogTable;
import com.ccit.pojo.Role;
import com.ccit.pojo.User;
import com.ccit.pojo.UserLog;
import com.ccit.service.UserLogService;
import com.ccit.service.UserService;
import com.ccit.utils.LoginIp;
import com.ccit.utils.ShiroUtil;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Inject
    private UserService userService;
    @Inject
    private UserLogService logService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(String username, String password, RedirectAttributes attributes, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password));
            String ip = LoginIp.getIp(request);
            subject.login(token);
           if( userService.addLog(ip)){
               return "redirect:/home";
           }else{
               return "redirect:/";
           }
        } catch (LockedAccountException exception) {
            attributes.addFlashAttribute("message", "该账号已被禁用");
            return "redirect:/";
        } catch (AuthenticationException exception) {
            attributes.addFlashAttribute("message", "账号或密码错误！");
            return "redirect:/";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/form",method =RequestMethod.GET )
    public String testPW(@RequestParam("origin")String password){
        User user = ShiroUtil.getPrincipal();
        if(user.getPassword().equals(DigestUtils.md5Hex(password))){
            return "true";
        }else{
            return "false";
        }
    }
    @ResponseBody
    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String alterPw(@RequestParam("new")String password){
        userService.alterPw(DigestUtils.md5Hex(password));
        return "success";
    }
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public String exit(RedirectAttributes attributes){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        attributes.addFlashAttribute("message","安全退出成功!");
        return "redirect:/";
    }
    @ResponseBody
    @RequestMapping(value = "/admin/log.json",method = RequestMethod.GET)
    public LogTable<UserLog> table(HttpServletRequest request){
       String draw = request.getParameter("draw");
       String start = request.getParameter("start");
       String size = request.getParameter("length");
       Long recordsTotal = logService.getTotal() ;
       Long recordsFiltered = logService.getTotal();
       List<UserLog> data = logService.findAll(start,size);
       LogTable<UserLog> logTable = new LogTable<UserLog>(draw,recordsTotal,recordsFiltered,data);
       return logTable;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String userManage(Model model){
        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user";
    }
    @ResponseBody
    @RequestMapping(value = "/user/data.json",method = RequestMethod.GET)
    public LogTable<User> getUserData(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String size = request.getParameter("length");

        Map<String,Object>map = Maps.newHashMap();
        map.put("start",start);
        map.put("size",size);
        List<User> data = userService.finByParam(map);
        Long recordsTotal = userService.getTotal() ;
        Long recordsFiltered = userService.getTotal();
        LogTable<User> userTable = new LogTable<User>(draw,recordsTotal,recordsFiltered,data);
        return userTable;
    }

    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(User user,RedirectAttributes attributes){
        logger.debug("user:{}",user);
        String password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(password);
        userService.addUser(user);
        attributes.addFlashAttribute("message","新增成功!");
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id")Integer id){
        userService.deleteById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value ="alter/{id}",method = RequestMethod.GET)
    public User alterUser(@PathVariable("id")Integer id){
        User user = userService.findById(id);
        return user;
    }
}

