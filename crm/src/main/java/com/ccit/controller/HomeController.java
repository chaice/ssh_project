package com.ccit.controller;

import com.ccit.pojo.LogTable;
import com.ccit.pojo.User;
import com.ccit.pojo.UserLog;
import com.ccit.service.UserLogService;
import com.ccit.service.UserService;
import com.ccit.utils.LoginIp;
import com.ccit.utils.ShiroUtil;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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
        if(user.getPassword().equals(password)){
            return "true";
        }else{
            return "false";
        }
    }
    @ResponseBody
    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String alterPw(@RequestParam("new")String password){
        userService.alterPw(password);
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
    public String userManage(){
        return "setting/user";
    }
}

