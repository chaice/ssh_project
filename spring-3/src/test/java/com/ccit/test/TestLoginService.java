package com.ccit.test;

import com.ccit.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestLoginService {
    @Inject
    private LoginService loginService;
    @Test
    public void testLoginService(){
        loginService.login("jim","234234","192.168.1.1");
    }
}
