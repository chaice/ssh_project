package com.ccit.utils;


import com.ccit.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
    public static User getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        return (User) subject.getPrincipal();
    }
    public static boolean isManager() {
        return getPrincipal().getRoleid().equals(2);
    }
}
