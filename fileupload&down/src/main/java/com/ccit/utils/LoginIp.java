package com.ccit.utils;

import javax.servlet.http.HttpServletRequest;


public class LoginIp {
    public static String getIp(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }
}
