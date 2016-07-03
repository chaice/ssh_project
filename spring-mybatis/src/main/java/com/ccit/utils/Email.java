package com.ccit.utils;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.inject.Named;


@Named
public class Email {
    /**
     *
     *
     * @param username 用户名
     * @param ip       用户登录时的Ip
     * @param time     用户登录的时间
     */

    public void sendEmail(String username, String ip,String time) {
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setAuthentication("cc911215@126.com","Q2889144");
        simpleEmail.setHostName("smtp.126.com");
        simpleEmail.setSmtpPort(25);
        simpleEmail.setCharset("utf-8");
        try {
            simpleEmail.setFrom("cc911215@126.com");
            simpleEmail.addTo("576237121@qq.com");
            simpleEmail.setMsg(time+",用户："+username+"在"+ip+"登录");
            simpleEmail.setSubject("开会");
            simpleEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

}
