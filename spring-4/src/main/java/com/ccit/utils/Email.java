package com.ccit.utils;

import com.ccit.exception.ServiceException;

import javax.inject.Named;

@Named
public class Email {
    public void sendEmail(){
        throw new ServiceException("邮件发送失败！！！");
    }
}
