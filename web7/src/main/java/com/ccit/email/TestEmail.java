package com.ccit.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class TestEmail {
    @Test
    public void testEmail(){
        SimpleEmail email = new SimpleEmail();
        email.setAuthentication("cc911215@126.com","Q2889144");
        email.setHostName("smtp.126.com");
        email.setSmtpPort(25);
        email.setCharset("utf-8");
        try {
            email.setFrom("cc911215@126.com");
            email.setSubject("这是一个邮件!");
            email.setMsg("下午开会！");
            email.addTo("576237121@qq.com");
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
}
