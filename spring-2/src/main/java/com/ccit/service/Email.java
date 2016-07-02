package com.ccit.service;

import com.ccit.exception.LoginException;

import javax.inject.Named;

@Named
public class Email {

    public void sendEmail(){
        throw new LoginException("发送邮件失败......");
    }
}
